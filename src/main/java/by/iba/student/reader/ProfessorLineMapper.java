package by.iba.student.reader;

import by.iba.student.common.Professor;

public class ProfessorLineMapper implements LineMapper<Professor> {

    @Override
    public Professor mapLine(String line) {
        String[] data = line.split(";");
        Professor professor = new Professor(data[1], data[2], data[3], data[4]);
        professor.setId(data[0]);
        return professor;
    }
}
