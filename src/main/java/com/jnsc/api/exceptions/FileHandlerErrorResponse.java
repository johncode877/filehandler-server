/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jnsc.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author firstdev
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileHandlerErrorResponse {

    private int status;
    private String message;
    private long timestamp;
}
