package com.expenseease.group.service;

import com.expenseease.group.dto.GroupDTO;
import com.expenseease.group.dto.UserDTO;
import com.expenseease.group.exceptions.InvalidGroupException;
import com.expenseease.group.exceptions.InvalidUserException;
import com.expenseease.group.model.Group;
import com.expenseease.group.model.User;
import com.expenseease.group.repository.GroupRepository;
import com.expenseease.group.repository.UserRepository;
import com.expenseease.group.util.Utility;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public GroupDTO createGroup(GroupDTO request, Long loggedInUserId) {
        Group group = new Group();
        group.setName(request.getName());
        group.setCreatedBy(loggedInUserId);
        group.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        Group newGroup = groupRepository.save(group);
        return Utility.mapObject(newGroup, GroupDTO.class);
    }

    public GroupDTO addUserToGroup(Long userId, Long groupId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new InvalidUserException("User not exist for id: "+userId);
        }
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            throw new InvalidGroupException("Group doesn't exist with id: "+groupId);
        }
        group.getUsers().add(user);
        Group updatedGroup = groupRepository.save(group);
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(updatedGroup.getId());
        groupDTO.setName(updatedGroup.getName());
        Set<UserDTO> users = updatedGroup.getUsers().stream()
                .map(u -> new UserDTO(u.getId(), u.getName(), u.getEmail()))
                .collect(Collectors.toSet());
        groupDTO.setUsers(users);
        return groupDTO;
    }

    public Set<GroupDTO> findGroupsForUser(Long userId) {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new InvalidUserException("User not exist for id: "+userId);
        }
        return user.getGroups().stream()
                .map(g -> new GroupDTO(g.getId(), g.getName())).collect(Collectors.toSet());
    }

    public Set<UserDTO> findUsersInGroup(Long groupId) {
        Group group = groupRepository.findById(groupId).get();
        if (group == null) {
            throw new InvalidGroupException("Group doesn't exist with id: "+groupId);
        }
        return group.getUsers().stream()
                .map(u -> new UserDTO(u.getId(), u.getName(), u.getEmail())).collect(Collectors.toSet());
    }

    public void deleteGroup(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(InvalidGroupException::new);
        groupRepository.delete(group);
    }

    public GroupDTO removeUserFromGroup(Long userId, Long groupId){
        Group group = groupRepository.findById(groupId).orElse(null);
        if (group == null) {
            throw new InvalidGroupException("Group doesn't exist with id: "+groupId);
        }
        User user = group.getUsers().stream().filter(users -> users.getId() == userId).findFirst().orElse(null);
        if(user == null){
            throw new InvalidUserException("User not exist for id: "+userId);
        }
        group.getUsers().remove(user);
        groupRepository.save(group);
        return Utility.mapObject(group, GroupDTO.class);
    }
}
