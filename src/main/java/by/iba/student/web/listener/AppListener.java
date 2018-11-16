package by.iba.student.web.listener;

import by.iba.student.filter.*;
import by.iba.student.repository.*;
import by.iba.student.common.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppListener implements ServletContextListener {
    private Repository<Integer, Student, StudentFilter> studentRepository;
    private Repository<String, Group, GroupFilter> groupRepository;
    private Repository<Integer, Professor, ProfessorFilter> professorRepository;
    private Repository<Integer, Subject, SubjectFilter> subjectRepository;
    private Repository<Integer, Marks, MarksFilter> marksRepository;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource dataSource = dataSource();
        ServletContext sc = sce.getServletContext();
        this.groupRepository = new Repository<String, Group, GroupFilter>(new GroupSQLMapper(), dataSource);
        sc.setAttribute("groupRepository", groupRepository);

        this.studentRepository = new Repository<Integer, Student, StudentFilter>(new StudentSQLMapper(), dataSource);
        sc.setAttribute("studentRepository", studentRepository);

        this.professorRepository = new Repository<Integer, Professor, ProfessorFilter>(new ProfessorSQLMapper(), dataSource);
        sc.setAttribute("professorRepository", professorRepository);

        this.subjectRepository = new Repository<Integer, Subject, SubjectFilter>(new SubjectSQLMapper(), dataSource);
        sc.setAttribute("subjectRepository", subjectRepository);

        this.marksRepository = new Repository<Integer, Marks, MarksFilter>(new MarkSQLMapper(), dataSource);
        sc.setAttribute("marksRepository", marksRepository);
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private DataSource dataSource() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BEGANSSDB");
            return ds;
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }

    private Properties getCurrProperties() {
        Properties property = new Properties();
        try {
            try (FileInputStream fis = new FileInputStream("/home/anna/uni/git/student-app/src/main/resources/config.properties")) {
                property.load(fis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}

