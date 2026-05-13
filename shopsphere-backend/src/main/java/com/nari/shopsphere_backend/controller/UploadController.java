package com.nari.shopsphere_backend.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    
    // Upload folder path
    private static final String UPLOAD_DIR =
            System.getProperty("user.dir")
            + "/uploads/";

    
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file")
            MultipartFile file) {

        try {

            
            // Create uploads folder if not exists
            File uploadFolder =
                    new File(UPLOAD_DIR);

            if (!uploadFolder.exists()) {

                uploadFolder.mkdir();
            }

            
            // Generate unique file name
            String fileName =
                    UUID.randomUUID()
                    + "_"
                    + file.getOriginalFilename();

            
            // Full file path
            String filePath =
                    UPLOAD_DIR + fileName;

            
            // Save file
            file.transferTo(
                    new File(filePath));

            
            // Return uploaded image path
            return ResponseEntity.ok(
                    "/uploads/" + fileName);

        }
        catch (IOException e) {

            return ResponseEntity
                    .internalServerError()
                    .body("Image Upload Failed");
        }
    }
}