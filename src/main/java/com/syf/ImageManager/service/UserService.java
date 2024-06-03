package com.syf.ImageManager.service;

import java.util.List;

import com.syf.ImageManager.model.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
    public User getUserById(Long id);

    public User saveUser(User user);

    public void deleteUser(Long id);
    
    public User updateUser(Long userId, User updatedUser);
}
