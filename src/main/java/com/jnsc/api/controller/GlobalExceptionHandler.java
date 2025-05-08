/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jnsc.api.controller;

import com.jnsc.api.exceptions.FileHandlerErrorResponse;
import com.jnsc.api.exceptions.FileHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

/**
 *
 * @author firstdev
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FileHandlerException.class)
    public ResponseEntity<FileHandlerErrorResponse> handleFileHandlerException(FileHandlerException e) {
        logger.error("FileHandlerException", e);

        FileHandlerErrorResponse error = new FileHandlerErrorResponse(e.getStatus().value(),
                e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(e.getStatus()).body(error);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<FileHandlerErrorResponse> handleException(MultipartException e) {

        logger.error("ClientException", e);

        FileHandlerErrorResponse error = new FileHandlerErrorResponse(HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FileHandlerErrorResponse> handleServerException(Exception e) {

        logger.error("ServerException", e);

        FileHandlerErrorResponse error = new FileHandlerErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<FileHandlerErrorResponse> handleMaxUploadSizeExceededException(Exception e) {
        logger.error("MaxUploadSizeExceededException: {}", e.getMessage());
       
        
         FileHandlerErrorResponse error = new FileHandlerErrorResponse(HttpStatus.BAD_REQUEST.value(),
                e.getMessage(), System.currentTimeMillis());
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        
    }

}
