package com.advance.user.controller;

import com.advance.user.controller.dto.CreateUserRequest;
import com.advance.user.service.UserManagementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserManagementService managementService;

    @GetMapping
    public ResponseEntity<Void> update(@RequestBody @Valid CreateUserRequest request) {
        managementService.normalSave(request);
        return ResponseEntity.ok().build();
    }

}
