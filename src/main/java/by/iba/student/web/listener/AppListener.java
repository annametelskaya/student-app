package by.iba.student.web.listener;

import by.iba.student.Repository.*;
import by.iba.student.common.*;
import by.iba.student.reader.*;
import by.iba.student.writer.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class AppListener implements ServletContextListener {
    private Repository<Integer, Student> studentRepository;
    private Repository<String, Group> groupRepository;
    private Repository<Integer, Professor> professorRepository;
    private Repository<Integer, Subject> subjectRepository;
    private Repository<Integer, Marks> marksRepository;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties property = getCurrProperties();

        ServletContext sc = sce.getServletContext();
        String groupPath = property.getProperty("group.file.path");
        List<Group> groups = new EntityFileReader<Group>(groupPath, new GroupLineMapper()).read();
        this.groupRepository = new Repository<String, Group>(new GroupSQLMapper(), groups);
        sc.setAttribute("groupRepository", groupRepository);

        String studentPath = property.getProperty("student.file.path");
        List<Student> students = new EntityFileReader<Student>(studentPath, new StudentLineMapper()).read();
        this.studentRepository = new Repository<Integer, Student>(new StudentSQLMapper(), students);
        sc.setAttribute("studentRepository", studentRepository);

        String professorPath = property.getProperty("professor.file.path");
        List<Professor> professors = new EntityFileReader<Professor>(professorPath, new ProfessorLineMapper()).read();
        this.professorRepository = new Repository<>(new ProfessorSQLMapper(), professors);
        sc.setAttribute("professorRepository", professorRepository);

        String subjectPath = property.getProperty("subject.file.path");
        List<Subject> subjects = new EntityFileReader<Subject>(subjectPath, new SubjectLineMapper()).read();
        this.subjectRepository = new Repository<>(new SubjectSQLMapper(), subjects);
        sc.setAttribute("subjectRepository", subjectRepository);

        String markPath = property.getProperty("mark.file.path");
        List<Marks> marks = new EntityFileReader<Marks>(markPath, new MarkLineMapper()).read();
        this.marksRepository = new Repository<Integer, Marks>(new MarkSQLMapper(), marks);
        sc.setAttribute("marksRepository", marksRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Properties property = getCurrProperties();
        try {
            ServletContext sc = sce.getServletContext();
            String studentPath = property.getProperty("student.file.path");
            List<Student> students = studentRepository.findAll();
            new StudentWriter(studentPath).write(students);

            String groupPath = property.getProperty("group.file.path");
            List<Group> groups = groupRepository.findAll();
            new GroupWriter(groupPath).write(groups);

            String professorPath = property.getProperty("professor.file.path");
            List<Professor> professors = professorRepository.findAll();
            new ProfessorWriter(professorPath).write(professors);

            String subjectPath = property.getProperty("subject.file.path");
            List<Subject> subjects = subjectRepository.findAll();
            new SubjectWriter(subjectPath).write(subjects);

            String markPath = property.getProperty("mark.file.path");
            List<Marks> marks = marksRepository.findAll();
            new MarkWriter(markPath).write(marks);
        } catch (IOException e) {
            System.out.println("can't save file");
        }
    }

    private Properties getCurrProperties() {
        Properties property = new Properties();
        try {
            try (FileInputStream fis = new FileInputStream("/home/anna/uni/git/student-app/src/main/resources/config.properties")) {
                property.load(fis);
            }
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        return property;
    }
}

