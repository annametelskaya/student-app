package by.iba.student.Repository;

import by.iba.student.common.Subject;

import java.util.*;

public class SubjectRepository {
    public final Map<String, Subject> subjects = new LinkedHashMap<>();


    public SubjectRepository(List<Subject> subjects) {
        if (subjects != null) {
            for (Subject subject : subjects) {
                this.subjects.put(subject.getId(), subject);
            }
        }
    }

    public Subject findSubjectById(String id) {
        for (Subject subject : subjects.values()) {
            if (subject.getId().equals(id)) {
                return subject;
            }
        }
        return null;
    }

    public List<Subject> findAll() {
        return new ArrayList<>(subjects.values());
    }

    public void create(Subject subject) {
        String id = UUID.randomUUID().toString();
        subject.setId(id);
        subjects.put(id, subject);
        //return student;
    }

    public void delete(String id) {
        subjects.remove(id);
    }

    public Subject update(Subject subject) {
        subjects.put(subject.getId(), subject);
        return subject;
    }
}
