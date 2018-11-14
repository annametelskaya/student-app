package by.iba.student.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntityFileReader<T> implements EntityReader<T> {
    private String path;
    LineMapper<T> mapper;

    public EntityFileReader(String path, LineMapper<T> mapper) {
        this.path = path;
        this.mapper = mapper;
    }

    @Override
    public List<T> read() {
        List<T> items = new ArrayList<T>();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = read.readLine()) != null) {
                T item = mapper.mapLine(line);
                items.add(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}
