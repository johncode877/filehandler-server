/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jnsc.api.service;

import com.jnsc.api.controller.dto.FileHandlerResponse;
import com.jnsc.api.entity.FileDetails;
import com.jnsc.api.exceptions.FileHandlerException;
import com.jnsc.api.util.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author firstdev
 */
@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    //private static final String BASE_FILE_PATH = System.getProperty("user.dir") + "/Uploads";
    @Value("${base.file.path}")
    private String baseFilePath;

    @Autowired
    FileDetailsService fileDetailsService;

    public FileHandlerResponse saveFile(MultipartFile file) {

        FileHandlerResponse fileHandlerResponse = new FileHandlerResponse();

        // Setting up the path of the file 
        String filePath = baseFilePath + File.separator; //+ file.getOriginalFilename();
        String fileUploadStatus;

        // Try block to check exceptions 
        try {
            String uuid = Utils.uuidString();
            logger.info("uuid: {}", uuid);

            // Creating an object of FileOutputStream class   
            FileOutputStream fout = new FileOutputStream(filePath.concat(uuid));
            int sizeFile = file.getBytes().length;
            fout.write(file.getBytes());

            // Closing the connection  
            fout.close();
            fileUploadStatus = "File Uploaded Successfully:" + file.getOriginalFilename();
            logger.info(fileUploadStatus);

            Date dateRegister = new Date();

            String hashFile = Utils.toHashFromBytes(file.getBytes()); 
            
            fileHandlerResponse.setId(uuid);
            fileHandlerResponse.setName(file.getOriginalFilename());
            fileHandlerResponse.setDate(dateRegister.toString());
            fileHandlerResponse.setHash(hashFile);
            fileHandlerResponse.setSize(sizeFile);
            
            //JsonUtils.writeJsonToFile(fileHandlerResponse, baseFilePath, uuid);
            
            FileDetails fileDetails = new FileDetails();
            fileDetails.setId(uuid);
            fileDetails.setName(file.getOriginalFilename());
            fileDetails.setDate(dateRegister);
            fileDetails.setHash(hashFile);
            fileDetails.setSize(sizeFile);

            fileDetailsService.save(fileDetails);
            
            return fileHandlerResponse;

        } // Catch block to handle exceptions 
        catch (Exception e) {
            logger.error("Exception", e);
            fileUploadStatus = "Error when uploading file: " + e.getMessage();
            throw new FileHandlerException(fileUploadStatus,HttpStatus.INTERNAL_SERVER_ERROR );
        }

       
    }

    public String[] getAllFiles() {

        // Creating a new File instance 
        File directory = new File(baseFilePath);

        // list() method returns an array of strings  
        // naming the files and directories  
        // in the directory denoted by this abstract pathname 
        String[] filenames = directory.list();

        // returning the list of filenames 
        return filenames;

    }

    public InputStreamResource getFile(String uuid) {

        // Checking whether the file requested for download exists or not 
        String fileUploadpath = baseFilePath;//System.getProperty("user.dir") + "/Uploads";

//        String[] filenames = this.getAllFiles();
//        boolean contains = Arrays.asList(filenames).contains(uuid);
//        if (!contains) {
//            throw new FileHandlerException("File Not Found", HttpStatus.NOT_FOUND);
//        }

        // read metadata from db 
        fileDetailsService.get(uuid);

        // Setting up the filepath 
        String filePath = fileUploadpath + File.separator + uuid;

        // Creating new file instance 
        File file = new File(filePath);

        // Creating a new InputStreamResource object         
        try {
            return new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            //logger.error("Exception", e);
            throw new FileHandlerException("Error to get File", HttpStatus.NOT_FOUND);
        }

    }

}
