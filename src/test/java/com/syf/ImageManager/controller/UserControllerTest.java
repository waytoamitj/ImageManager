package com.syf.ImageManager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.syf.ImageManager.model.Image;
import com.syf.ImageManager.model.User;
import com.syf.ImageManager.service.ImgurService;
import com.syf.ImageManager.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private ImgurService imgurService;

    @InjectMocks
    private UserController userController;

    @Test
    void testGetAllUsers() {
        // Setup
        List<User> users = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(users);

        // Execute
        List<User> result = userController.getAllUsers();

        // Verify
        assertEquals(users, result);
    }

    @Test
    void testRegisterUser() {
        // Setup
        User user = new User();
        when(userService.saveUser(user)).thenReturn(user);

        // Execute
        ResponseEntity<String> response = userController.registerUser(user);

        // Verify
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }

//    @Test
//    void testUploadImage() throws IOException {
//        // Setup
//        Long userId = 1L;
//        MultipartFile file = mock(MultipartFile.class);
//        byte[] fileContent = "test".getBytes();
//        when(file.getBytes()).thenReturn(fileContent);
//
//        User user = new User();
//        Image image = new Image();
//        when(userService.getUserById(userId)).thenReturn(user);
//        when(imgurService.uploadImage(fileContent)).thenReturn("image_url");
//        when(userService.updateUser(userId, user)).thenReturn(user);
//
//        // Execute
//        ResponseEntity<String> response = userController.uploadImage(userId, file);
//
//        // Verify
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, user.getImages().size());
//        assertEquals(image, user.getImages().get(0));
//    }

}
