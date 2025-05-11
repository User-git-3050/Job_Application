package com.example.msapplication.service.inter;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String saveFile(MultipartFile file);
}
