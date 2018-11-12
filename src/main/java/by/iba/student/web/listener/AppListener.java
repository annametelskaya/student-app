package by.iba.student.web.listener;

import by.iba.student.Repository.GroupRepository;
import by.iba.student.Repository.ProfessorRepository;
import by.iba.student.Repository.StudentRepository;
import by.iba.student.common.Group;
import by.iba.student.common.Professor;
import by.iba.student.common.Student;
import by.iba.student.reader.GroupReader;
import by.iba.student.reader.ProfessorReader;
import by.iba.student.reader.StudentReader;
import by.iba.student.writer.GroupWriter;
import by.iba.student.writer.ProfessorWriter;
import by.iba.student.writer.StudentWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AppListener implements ServletContextListener {
    private StudentRepository studentRepository;
    private GroupRepository groupRepository;
    private ProfessorRepository professorRepository;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext sc = sce.getServletContext();
            String studentPath = sc.getInitParameter("student.file.path");
            List<Student> students = new StudentReader(studentPath).reader();
            this.studentRepository = new StudentRepository(students);
            sc.setAttribute("studentRepository", studentRepository);

            String groupPath = sc.getInitParameter("group.file.path");
            List<Group> groups = new GroupReader(groupPath).reader();
            this.groupRepository = new GroupRepository(groups);
            sc.setAttribute("groupRepository", groupRepository);

            String professorPath = sc.getInitParameter("professor.file.path");
            List<Professor> professors = new ProfessorReader(professorPath).reader();
            this.professorRepository = new ProfessorRepository(professors);
            sc.setAttribute("professorRepository", professorRepository);
        } catch (IOException e) {
            try {
                throw new ServletException(e);
            } catch (ServletException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ServletContext sc = sce.getServletContext();
            String studentPath = sc.getInitParameter("student.file.path");
            List<Student> students = studentRepository.findAll();
            new StudentWriter(studentPath).write(students);

            String groupPath = sc.getInitParameter("group.file.path");
            List<Group> groups = groupRepository.findAll();
            new GroupWriter(groupPath).write(groups);

            String professorPath = sc.getInitParameter("professor.file.path");
            List<Professor> professors = professorRepository.findAll();
            new ProfessorWriter(professorPath).write(professors);
        } catch (IOException e) {
            System.out.println("can't save file");
        }
    }
}

