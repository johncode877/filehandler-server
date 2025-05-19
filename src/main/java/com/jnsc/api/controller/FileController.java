/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jnsc.api.controller;

import com.jnsc.api.controller.dto.FileHandlerResponse;
import com.jnsc.api.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author firstdev
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class FileController {

    @Autowired
    FileService fileService;

    // Uploading a file 
    @PostMapping(value = "/upload")
    public ResponseEntity<FileHandlerResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(fileService.saveFile(file), HttpStatus.OK);
    }

    // Getting list of filenames that have been uploaded 
//    @GetMapping(value = "/files")
//    public String[] getFiles() {
//        return fileService.getAllFiles();
//    }
    // Downloading a file 
    @GetMapping(value = "/download/{uuid:.+}")
    public ResponseEntity downloadFile(@PathVariable("uuid") String uuid) {

        // Creating a new InputStreamResource object 
        InputStreamResource resource = fileService.getFile(uuid);

        // Setting up values for contentType and headerValue 
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);

    }

    @GetMapping(value = "/info/{uuid:.+}")
    public ResponseEntity<FileHandlerResponse> getFileInfo(@PathVariable("uuid") String uuid) {

        // Creating a new InputStreamResource object 
        FileHandlerResponse fileInfo = fileService.getFileInfo(uuid);

        return new ResponseEntity<>(fileInfo, HttpStatus.OK);

    }

}
