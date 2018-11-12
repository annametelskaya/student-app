package by.iba.student.listener;

import by.iba.student.Repository.StudentRep;
import by.iba.student.common.Student;
import by.iba.student.reader.StudentReader;
import by.iba.student.writer.StudentWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AppListener implements ServletContextListener {
    private StudentRep rep;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext sc = sce.getServletContext();
            String path = sc.getInitParameter("student.file.path");
            List<Student> students = new StudentReader(path).reader();
            this.rep = new StudentRep(students);
            sc.setAttribute("studentRepository", rep);
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
            String path = sc.getInitParameter("student.file.path");
            List<Student> students = rep.findAll();
            new StudentWriter(path).write(students);
        } catch (IOException e) {
            System.out.println("can't save file");
        }
    }
}

