package com.syf.ImageManager.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class ImgurServiceImplTest {

    @Mock
    private WebClient webClient;

    @InjectMocks
    private ImgurServiceImpl imgurService;

//    @Test
//    void testUploadImage() {
//        // Setup
//        byte[] imageBytes = "testImage".getBytes();
//        String expectedResponse = "imageUrl";
//        when(webClient.post()).thenReturn(webClient);
//        when(webClient.uri("")).thenReturn(webClient);
//        when(webClient.contentType(MediaType.MULTIPART_FORM_DATA)).thenReturn(webClient);
//        when(webClient.body(any(BodyInserters.MultipartInserter.class))).thenReturn(webClient);
//        when(webClient.retrieve()).thenReturn(webClient);
//        when(webClient.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));
//
//        // Execute
//        String result = imgurService.uploadImage(imageBytes);
//
//        // Verify
//        assertEquals(expectedResponse, result);
//    }
//
//    @Test
//    void testGetImage() {
//        // Setup
//        String imageId = "testImageId";
//        Resource expectedResource = new ByteArrayResource("testImageData".getBytes());
//        when(webClient.get()).thenReturn(webClient);
//        when(webClient.uri("/" + imageId)).thenReturn(webClient);
//        when(webClient.retrieve()).thenReturn(webClient);
//        when(webClient.bodyToMono(Resource.class)).thenReturn(Mono.just(expectedResource));
//
//        // Execute
//        Resource result = imgurService.getImage(imageId);
//
//        // Verify
//        assertEquals(expectedResource, result);
//    }
//
//    @Test
//    void testDeleteImage() {
//        // Setup
//        String imageDeleteHash = "testImageDeleteHash";
//        String expectedResponse = "success";
//        when(webClient.delete()).thenReturn(webClient);
//        when(webClient.uri("/" + imageDeleteHash)).thenReturn(webClient);
//        when(webClient.retrieve()).thenReturn(webClient);
//        when(webClient.bodyToMono(String.class)).thenReturn(Mono.just(expectedResponse));
//
//        // Execute
//        String result = imgurService.deleteImage(imageDeleteHash);
//
//        // Verify
//        assertEquals(expectedResponse, result);
//    }
}

