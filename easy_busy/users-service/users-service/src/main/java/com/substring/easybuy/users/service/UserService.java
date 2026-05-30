package com.substring.easybuy.users.service;

import com.substring.easybuy.users.dto.UserDto;
import com.substring.easybuy.users.entity.Role;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(UUID id);

    UserDto getUserByEmail(String email);

    List<UserDto> getAllUsers();

    UserDto updateUser(UUID id, UserDto userDto);

    void deleteUser(UUID id);

    void changeUserRole(UUID id, Role role);

}
