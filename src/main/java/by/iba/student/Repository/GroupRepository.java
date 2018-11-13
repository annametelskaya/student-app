package by.iba.student.Repository;

import by.iba.student.common.Group;

import java.util.*;

public class GroupRepository {
    public final Map<String, Group> groups = new LinkedHashMap<>();


    public GroupRepository(List<Group> groups) {
        if (groups != null) {
            for (Group group : groups) {
                this.groups.put(group.getId(), group);
            }
        }
    }

    public Group findGroupById(String id) {
        for (Group group : groups.values()) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
    }

    public List<Group> findAll() {
        return new ArrayList<>(groups.values());
    }

    public void create(Group group) {
        String id = UUID.randomUUID().toString();
        group.setId(id);
        groups.put(id, group);
        //   return group;
    }

    public void delete(String id) {
        groups.remove(id);
    }

    public Group update(Group group) {
        groups.put(group.getId(), group);
        return group;
    }
}

