package com.expenseease.group.controller;

import com.expenseease.group.dto.ExpenseEaseResponse;
import com.expenseease.group.dto.GroupDTO;
import com.expenseease.group.dto.UserDTO;
import com.expenseease.group.model.User;
import com.expenseease.group.service.GroupService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ExpenseEaseResponse> createGroup(@RequestHeader("Authorization") String token, @RequestBody GroupDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();
        GroupDTO group = groupService.createGroup(request, loggedInUser.getId());
        return ResponseEntity.ok(ExpenseEaseResponse.success("User created successfully", group));
    }

    @PostMapping("/groups/{groupId}/users/{userId}")
    public ResponseEntity<ExpenseEaseResponse> addUserToGroup(@RequestHeader("Authorization") String token,
                                   @PathVariable Long userId, @PathVariable Long groupId) {
        GroupDTO group = groupService.addUserToGroup(userId, groupId);
         return ResponseEntity.ok(ExpenseEaseResponse.success("User successfully added to group", group));
    }

    @GetMapping("users/{userId}/groups")
    public ResponseEntity<ExpenseEaseResponse> findGroupsForUser(@RequestHeader("Authorization") String token, @PathVariable Long userId) {
        Set<GroupDTO> group = groupService.findGroupsForUser(userId);
        return ResponseEntity.ok(ExpenseEaseResponse.success("Groups successfully fetched for user "+userId, group));
    }

    @GetMapping("/groups/{groupId}/users")
    public ResponseEntity<ExpenseEaseResponse> findUsersInGroup(@RequestHeader("Authorization") String token, @PathVariable Long groupId) {
        Set<UserDTO> group = groupService.findUsersInGroup(groupId);
        return ResponseEntity.ok(ExpenseEaseResponse.success("User found successfully", group));
    }

    @DeleteMapping("/groups/{groupId}")
    public void deleteGroup(@RequestHeader("Authorization") String token,
                            @PathVariable Long groupId){
        groupService.deleteGroup(groupId);
    }

    @PutMapping("/groups/{groupId}")
    public ResponseEntity<ExpenseEaseResponse> exitFromGroup(@RequestHeader("Authorization") String token, @PathVariable Long groupId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();
        groupService.exitFromGroup(loggedInUser.getId(), groupId);
        return ResponseEntity.ok(ExpenseEaseResponse.success("Exit from group "+ loggedInUser.getName()));
    }
}
