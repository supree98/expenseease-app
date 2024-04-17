package com.expenseease.group.controller;

import com.expenseease.group.dto.GroupDTO;
import com.expenseease.group.dto.UserDTO;
import com.expenseease.group.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/api")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/groups")
    public GroupDTO createGroup(@RequestHeader("Authorization") String token, @RequestBody GroupDTO request) {
        return groupService.createGroup(request);
    }

    @PostMapping("/groups/{groupId}/users/{userId}")
    public GroupDTO addUserToGroup(@RequestHeader("Authorization") String token,
                                   @PathVariable Long userId, @PathVariable Long groupId) {
        return groupService.addUserToGroup(userId, groupId);
    }

    @GetMapping("users/{userId}/groups")
    public Set<GroupDTO> findGroupsForUser(@RequestHeader("Authorization") String token,
                                           @PathVariable Long userId) {
        return groupService.findGroupsForUser(userId);
    }

    @GetMapping("/groups/{groupId}/users")
    public Set<UserDTO> findUsersInGroup(@RequestHeader("Authorization") String token,
                                         @PathVariable Long groupId) {
        return groupService.findUsersInGroup(groupId);
    }
}
