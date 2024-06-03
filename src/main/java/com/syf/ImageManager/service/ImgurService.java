package com.syf.ImageManager.service;

import org.springframework.core.io.Resource;

public interface ImgurService {

    public String uploadImage(byte[] imageBytes);
    
    public Resource getImage(String imageId);
    
    public String deleteImage(String imageDeleteHash);
}
