package com.advance.user.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(@NotBlank String name, @NotBlank String age) {

}
