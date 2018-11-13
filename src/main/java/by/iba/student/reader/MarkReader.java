package by.iba.student.reader;

import by.iba.student.common.Marks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarkReader {
    private final String path;

    public MarkReader(String path) {
        this.path = path;
    }

    public List<Marks> reader() throws IOException {
        List<Marks> marks = new ArrayList<Marks>();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] data = line.split(";");
                Marks subject;
                if (data.length > 5) {
                    subject = new Marks(data[1], data[2], data[3], data[4], data[5]);
                } else {
                    subject = new Marks(data[1], data[2], data[3], data[4], "");
                }
                subject.setId(data[0]);
                marks.add(subject);
            }

        }
        return marks;
    }
}
