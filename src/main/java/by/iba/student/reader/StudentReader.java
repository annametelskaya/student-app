package by.iba.student.reader;

import by.iba.student.common.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentReader {
    private final String path;

    public StudentReader(String path) {
        this.path = path;
    }

//    public List<Student> reader() throws IOException {
//        List<Student> students = new ArrayList<Student>();
//        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
//            String line;
//            while ((line = read.readLine()) != null) {
//                String[] data = line.split(";");
//                Student student = new Student(data[1], data[2], data[3]);
//                student.setId(data[0]);
//                students.add(student);
//            }
//
//        }
//        return students;
   // }
}
