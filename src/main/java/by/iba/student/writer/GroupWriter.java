package by.iba.student.writer;

import by.iba.student.common.Group;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GroupWriter {
    private final String path;

    public GroupWriter(String path) {
        this.path = path;
    }

    public void write(List<Group> groups) throws IOException {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(path))) {
            for (Group gr : groups) {
                String line = String.format("%s;%s;%s",
                        gr.getId(),
                        gr.getGroupNumber(),
                        gr.getNumber());
                write.write(line);
                write.newLine();
            }
        }
    }
}
