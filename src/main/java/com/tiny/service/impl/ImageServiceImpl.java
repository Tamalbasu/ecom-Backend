package com.tiny.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tiny.service.ImageService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public byte[] getImage(int id) {
        try {
            int k = id % 10;
            String p = "src/main/resources/static/" + k + ".PNG";
            File img = new File("src/main/resources/static/" + k + ".PNG");
            byte[] fileContent = Files.readAllBytes(img.toPath());
            Map<Integer,String> k1  =  new HashMap<>();
            k1.put(id,p);
            loadMapFromFileAndSaveIt(k1);
            return fileContent;
        } catch (IOException e) {
            return new byte[0];
        }


    }

    @Override
    public String getImages() {
        Map<Object, Object> map = loadMap("src/main/resources/images.json");
        return map.toString();
    }

    public void loadMapFromFileAndSaveIt(Map<Integer,String> k1 ) {
        Map<Object, Object> map = loadMap("src/main/resources/images.json");
        if(map==null){
            map = new HashMap<>();
            map.putAll(k1);
            save(map, "src/main/resources/images.json");
        }else{
            System.out.println(map.keySet());
            map.putAll(k1);
            save(map, "src/main/resources/images.json");
        }


    }

    private Map<Object, Object> loadMap(String string) {
        ObjectMapper mapper = new ObjectMapper();
        File img = new File(string);
        try {
            String fileContent = Files.readString(img.toPath(), StandardCharsets.UTF_8);
            return mapper.readValue(fileContent, HashMap.class);
        }catch (Exception e){

        }
           return null;
    }

    private void save(Map<Object, Object> map, String path) {
        try (PrintWriter out = new PrintWriter(path)) {
            out.println(toString(map));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toString(Object obj) {
        try (StringWriter w = new StringWriter();) {
            new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true).writeValue(w, obj);
            return w.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
