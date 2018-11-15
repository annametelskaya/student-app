//package by.iba.student.Repository;
//
//import by.iba.student.common.Subject;
//
//import java.util.*;
//
//public class SubjectRepository {
//    public final Map<Integer, Subject> subjects = new LinkedHashMap<>();
//
//
//    public SubjectRepository(List<Subject> subjects) {
//        if (subjects != null) {
//            for (Subject subject : subjects) {
//                this.subjects.put(subject.getId(), subject);
//            }
//        }
//    }
//
//    public Subject findSubjectById(int id) {
//        for (Subject subject : subjects.values()) {
//            if (subject.getId() == id) {
//                return subject;
//            }
//        }
//        return null;
//    }
//
//    public List<Subject> findAll() {
//        return new ArrayList<>(subjects.values());
//    }
//
//    public void create(Subject subject) {
//        int id = subjects.size() + 1;
//        subject.setId(id);
//        subjects.put(id, subject);
//        //return student;
//    }
//
//    public void delete(String id) {
//        subjects.remove(id);
//    }
//
//    public Subject update(Subject subject) {
//        subjects.put(subject.getId(), subject);
//        return subject;
//    }
//}
