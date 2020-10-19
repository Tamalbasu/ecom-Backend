package com.tiny.service;

import org.springframework.stereotype.Service;


public interface ImageService {
    byte[] getImage(int id);
    String getImages();
}
