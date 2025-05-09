/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jnsc.api.service;

import com.jnsc.api.entity.FileDetails;
import com.jnsc.api.exceptions.FileHandlerException;
import com.jnsc.api.repository.FileDetailsRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author John Sevillano <john.sevillano@gmail.com>
 */
@Service
public class FileDetailsService {
    
    
    private static final Logger logger = LoggerFactory.getLogger(FileDetailsService.class);
    
    @Autowired
    FileDetailsRepository fileDetailsRepository;

    public void save(FileDetails fileDetails) {
        fileDetailsRepository.save(fileDetails);
    }

    
    public FileDetails get(String id){

        Optional<FileDetails> optional = fileDetailsRepository.findById(id);

        if(optional.isEmpty()){
            logger.error("fileDetails doesn't exist!!!");
            throw new FileHandlerException("FILE_DETAILS_NOT_EXIST", HttpStatus.BAD_REQUEST);
        }

        return optional.get();
    }
    
    
}


