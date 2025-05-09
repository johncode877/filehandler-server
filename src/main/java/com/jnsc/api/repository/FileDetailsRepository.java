/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jnsc.api.repository;

import com.jnsc.api.entity.FileDetails;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author John Sevillano <john.sevillano@gmail.com>
 */
public interface FileDetailsRepository extends JpaRepository<FileDetails, Long> {

    Optional<FileDetails> findById(String id);

}
