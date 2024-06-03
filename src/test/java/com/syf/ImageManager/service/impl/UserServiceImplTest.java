package com.syf.ImageManager.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.syf.ImageManager.model.User;
import com.syf.ImageManager.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetAllUsers() {
        // Setup
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        // Execute
        List<User> result = userService.getAllUsers();

        // Verify
        assertEquals(users, result);
    }

    @Test
    void testGetUserById() {
        // Setup
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Execute
        User result = userService.getUserById(userId);

        // Verify
        assertEquals(user, result);
    }

    @Test
    void testGetUserByIdNotFound() {
        // Setup
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Execute
        User result = userService.getUserById(userId);

        // Verify
        assertNull(result);
    }

    @Test
    void testSaveUser() {
        // Setup
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        // Execute
        User result= userService.saveUser(user);

        // Verify
        assertEquals(user, result);
    }

    @Test
    void testDeleteUser() {
        // Setup
        Long userId = 1L;

        // Execute
        assertDoesNotThrow(() -> userService.deleteUser(userId));

        // Verify
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testUpdateUser() {
        // Setup
        Long userId = 1L;
        User existingUser = new User();
        User updatedUser = new User();
        updatedUser.setUsername("updatedUsername");
        updatedUser.setPassword("updatedPassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Execute
        User result = userService.updateUser(userId, updatedUser);

        // Verify
        assertEquals(updatedUser.getUsername(), result.getUsername());
        assertEquals(updatedUser.getPassword(), result.getPassword());
    }

    @Test
    void testUpdateUserNotFound() {
        // Setup
        Long userId = 1L;
        User updatedUser = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Execute and Verify
        assertThrows(RuntimeException.class, () -> userService.updateUser(userId, updatedUser));
    }
}
