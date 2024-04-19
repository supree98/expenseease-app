package com.expenseease.group.controller;

import com.expenseease.group.dto.GroupDTO;
import com.expenseease.group.dto.UserDTO;
import com.expenseease.group.model.User;
import com.expenseease.group.service.GroupService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();
        return groupService.createGroup(request, loggedInUser.getId());
    }

    @PostMapping("/groups/{groupId}/users/{userId}")
    public GroupDTO addUserToGroup(@RequestHeader("Authorization") String token,
                                   @PathVariable Long userId, @PathVariable Long groupId) {
        return groupService.addUserToGroup(userId, groupId);
    }

    @GetMapping("users/{userId}/groups")
    public Set<GroupDTO> findGroupsForUser(@RequestHeader("Authorization") String token, @PathVariable Long userId) {
        return groupService.findGroupsForUser(userId);
    }

    @GetMapping("/groups/{groupId}/users")
    public Set<UserDTO> findUsersInGroup(@RequestHeader("Authorization") String token, @PathVariable Long groupId) {
        return groupService.findUsersInGroup(groupId);
    }

    @DeleteMapping("/groups/{groupId}")
    public void deleteGroup(@RequestHeader("Authorization") String token,
                            @PathVariable Long groupId){
        groupService.deleteGroup(groupId);
    }

    @PutMapping("/groups/{userId}/{groupId}")
    public GroupDTO exitFromGroup(@RequestHeader("Authorization") String token,
                                  @PathVariable Long userId, @PathVariable Long groupId){
        return groupService.removeUserFromGroup(userId, groupId);
    }
}
