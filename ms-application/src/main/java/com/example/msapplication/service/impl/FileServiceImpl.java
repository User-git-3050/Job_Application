package com.example.msapplication.service.impl;

import com.example.msapplication.service.inter.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;


    @Override
    public String saveFile(MultipartFile file) {
        try{
            String fileName = System.currentTimeMillis()+ "_"+file.getOriginalFilename();
            Path path = Paths.get(uploadDir+"/"+fileName);

            Files.createDirectories(path.getParent());

            Files.copy(file.getInputStream() , path);

            return path.toString();
        }
        catch (Exception e){
            throw new RuntimeException("Failed to save file");
        }
    }
}
