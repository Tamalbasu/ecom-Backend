package com.tiny.controller;

import com.tiny.service.ImageService;
import com.tiny.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.nio.file.Files;

@RestController
public class HelloController {
    @Autowired
    ImageServiceImpl imageService;

    @RequestMapping(value = "/images/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> value(@PathVariable int id)  {
        byte[] fileContent = imageService.getImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileContent); }

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    public String listOfImages( ) {

        return imageService.getImages(); }

}
