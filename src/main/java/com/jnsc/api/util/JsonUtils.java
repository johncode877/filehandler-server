/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jnsc.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author firstdev
 */
public class JsonUtils {

    public static void writeJsonToFile(Object object, String rootPath, String name) throws IOException {

        // Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        // Enable pretty printing for better readability
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Write object to JSON file
        String filePath = rootPath + File.separator + name.concat(".json");
        mapper.writeValue(new File(filePath), object);

    }

}
