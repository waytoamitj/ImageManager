package com.syf.ImageManager.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.syf.ImageManager.service.ImgurService;

/**
 * 
 */
@Service
public class ImgurServiceImpl implements ImgurService {

	private final WebClient webClient;

	private String clientId = "8c482d4fd14b7c4";
	private String apiUrl = "https://api.imgur.com/3/image";

	public ImgurServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(apiUrl)
				.defaultHeader(HttpHeaders.AUTHORIZATION, "Client-ID " + clientId).build();
	}

	/**
	 *
	 */
	@Override
	public String uploadImage(byte[] imageBytes) {
		return webClient.post().uri("").contentType(MediaType.MULTIPART_FORM_DATA)
				.body(BodyInserters.fromMultipartData("image", imageBytes)).retrieve().bodyToMono(String.class).block();
	}

	/**
	 *
	 */
	@Override
	public Resource getImage(String imageId) {
		// handle fetch when Image is deleted
		return webClient.get().uri("/"+imageId).retrieve().bodyToMono(Resource.class).block();
	}

	/**
	 *
	 */
	@Override
	public String deleteImage(String imageDeleteHash) {
		return webClient.delete().uri("/"+imageDeleteHash).retrieve().bodyToMono(String.class).block();
	}

}