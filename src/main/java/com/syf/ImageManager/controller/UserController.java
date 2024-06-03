package com.syf.ImageManager.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syf.ImageManager.model.Image;
import com.syf.ImageManager.model.ImgurResponse;
import com.syf.ImageManager.model.User;
import com.syf.ImageManager.service.ImgurService;
import com.syf.ImageManager.service.UserService;

/**
 * Entry point for User registration, list of registered users, CRUD operation for user, Image upload by user to Imgur, Image fetch and Image deletion
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ImgurService imgurService;

	/**
	 * @return list of registered users
	 */
	@GetMapping("/list")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * @param user User to register
	 * @return Response status for User registration
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	}

	/**
	 * @param userId registered user id
	 * @return Response status for user deletion
	 */
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok().body("User deleted successfully");
	}

	/**
	 * @param userId  registered user id
	 * @param userToUpdate  User to update
	 * @return Response message for user update
	 */
	@PutMapping("/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User userToUpdate) {
		userService.updateUser(userId, userToUpdate);
		return ResponseEntity.ok().body("User updated successfully");
	}

	/**
	 * @param userId registered user id
	 * @param file image to upload
	 * @return Imgur Response JSON string for Image upload in Imgur.
	 * @throws IOException
	 */
	@PostMapping("/{userId}/images/upload")
	public ResponseEntity<String> uploadImage(@PathVariable Long userId, @RequestParam("file") MultipartFile file)
			throws IOException {

		User user = userService.getUserById(userId);
		// Check if user is authenticated
		if (user != null) {
			String imageUrl = imgurService.uploadImage(file.getBytes());
			Image image = buildImageFromImgurResponse(imageUrl);

			// Associate uploaded image to User
			user.getImages().add(image);
			userService.updateUser(userId, user);

			return ResponseEntity.ok(imageUrl);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	/**
	 * @param userId registered user id
	 * @param imageId image id to retrieve
	 * @return Resource fetch from Imgur service for particular ImageId
	 */
	@GetMapping("/{userId}/images/{imageId}")
	public ResponseEntity<Resource> getImage(@PathVariable Long userId, @PathVariable String imageId) {

		boolean allowedToFetch = false;
		User user = userService.getUserById(userId);

		// Check if user is authenticated to fetch
		if (user != null) {

			// only ImageId associated with user is allowed to fetch
			allowedToFetch = user.getImages().stream().filter(s -> s.getId().equalsIgnoreCase(imageId)).findAny()
					.isPresent();

			if (!allowedToFetch) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
			}

			Resource imageResource = imgurService.getImage(imageId);
			if (imageResource != null) {
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageResource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
	}

	/**
	 * @param userId registered user id
	 * @param imageDeleteHash image hash to delete fror Imgur
	 * @return Response message if User is able to successfully delete the image or
	 *         Unauthorized to delete or User does not exist
	 */
	@DeleteMapping("/{userId}/images/{imageDeleteHash}")
	public ResponseEntity<String> deleteImage(@PathVariable Long userId, @PathVariable String imageDeleteHash) {

		boolean allowedToDelete = false;
		User user = userService.getUserById(userId);

		// Check if user is authenticated to delete image
		if (user != null) {

			// only associated user is allowed to delete image
			allowedToDelete = user.getImages().stream().filter(s -> s.getDeleteHash().equalsIgnoreCase(imageDeleteHash))
					.findAny().isPresent();

			if (!allowedToDelete) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not allowed to delete Image");
			}

			
			imgurService.deleteImage(imageDeleteHash);			// delete image from imgur
			
			Iterator<Image> iterator = user.getImages().iterator();
			while (iterator.hasNext()) {
			    if (iterator.next().getDeleteHash().equalsIgnoreCase(imageDeleteHash)) {
			        iterator.remove(); // Remove the current element
			    }
			}
			
			userService.updateUser(user.getId(), user);			// remove from user profile
			
			return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User does not exist");
		}
	}

	private Image buildImageFromImgurResponse(String imgurResponseJsonString)
			throws JsonMappingException, JsonProcessingException {

		ObjectMapper objectMapper = new ObjectMapper();
		ImgurResponse imgurReponse = objectMapper.readValue(imgurResponseJsonString, ImgurResponse.class);

		Image image = new Image();
		image.setId(imgurReponse.getData().getId());
		image.setDeleteHash(imgurReponse.getData().getDeleteHash());
		image.setLink(imgurReponse.getData().getLink());
		image.setType(imgurReponse.getData().getType());
		image.setSize(imgurReponse.getData().getSize());

		return image;
	}

}
