package by.iba.student.reader;

import by.iba.student.common.Group;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GroupReader {
    private final String path;

    public GroupReader(String path) {
        this.path = path;
    }

    public List<Group> reader() throws IOException {
        List<Group> groups = new ArrayList<Group>();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] data = line.split(";");
                Group group = new Group(data[1]);
                group.setId(data[0]);
                group.setNumber(data[2]);
                groups.add(group);
            }

        }
        return groups;
    }
}

