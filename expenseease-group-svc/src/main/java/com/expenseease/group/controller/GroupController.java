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

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins = "*")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/groups")
    public ResponseEntity<ExpenseEaseResponse> createGroup(@RequestHeader("Authorization") String token, @RequestBody GroupDTO request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = (User) authentication.getPrincipal();
        GroupDTO newGroup = groupService.createGroup(request, loggedInUser.getId());
        return ResponseEntity.ok(ExpenseEaseResponse.success("Group created successfully.", Map.of("group", newGroup)));
    }

    @PostMapping("/groups/{groupId}/users/{userId}")
    public ResponseEntity<ExpenseEaseResponse> addUserToGroup(@RequestHeader("Authorization") String token,
                                                              @PathVariable Long userId, @PathVariable Long groupId) {
        GroupDTO updatedGroup = groupService.addUserToGroup(userId, groupId);
        return ResponseEntity.ok(ExpenseEaseResponse.success("User added to the group successfully.", Map.of("group", updatedGroup)));
    }

    @GetMapping("users/{userId}/groups")
    public ResponseEntity<ExpenseEaseResponse> findGroupsForUser(@RequestHeader("Authorization") String token, @PathVariable Long userId) {
        Set<GroupDTO> groups = groupService.findGroupsForUser(userId);
        return ResponseEntity.ok(ExpenseEaseResponse.success("Groups fetched successfully.", Map.of("groups", groups)));
    }

    @GetMapping("/groups/{groupId}/users")
    public ResponseEntity<ExpenseEaseResponse> findUsersInGroup(@RequestHeader("Authorization") String token, @PathVariable Long groupId) {
        Set<UserDTO> members = groupService.findUsersInGroup(groupId);
        return ResponseEntity.ok(ExpenseEaseResponse.success("Group members fetched successfully.", Map.of("members", members)));
    }
}
