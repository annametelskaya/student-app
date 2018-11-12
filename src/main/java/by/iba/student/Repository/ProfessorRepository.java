package by.iba.student.Repository;

import by.iba.student.common.Professor;

import java.util.*;

public class ProfessorRepository {
    public final Map<String, Professor> professors = new LinkedHashMap<>();


    public ProfessorRepository(List<Professor> professors) {
        if (professors != null) {
            for (Professor professor : professors) {
                this.professors.put(professor.getId(), professor);
            }
        }
    }

    public Professor findProfessorById(String id) {
        for (Professor professor : professors.values()) {
            if (professor.getId().equals(id)) {
                return professor;
            }
        }
        return null;
    }

    public List<Professor> findAll() {
        return new ArrayList<>(professors.values());
    }

    public void create(Professor professor) {
        String id = UUID.randomUUID().toString();
        professor.setId(id);
        professors.put(id, professor);
        //return student;
    }

    public void delete(String id) {
        professors.remove(id);
    }

    public Professor update(Professor professor) {
        professors.put(professor.getId(), professor);
        return professor;
    }
}
