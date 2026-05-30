package com.substring.easybuy.users.dto;

import com.substring.easybuy.users.entity.Role;

import java.util.UUID;

public record ChangeRoleRequest(
        UUID userId,
        Role role
) {
}
