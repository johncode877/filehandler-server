/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jnsc.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.util.Date;


/**
 *
 * @author John Sevillano <john.sevillano@gmail.com>
 */
@Entity
public class FileDetails {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    
    @Id
    @GeneratedValue(
        generator = "file_details_seq",
        strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
        name = "file_details_seq",
        allocationSize = 1
    )
    private Long file_id;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String hash;

    @Column(nullable = false)
    private int size;

    @Column(nullable = false)
    private Status status = Status.ENABLED;

    public Long getFile_id() {
        return file_id;
    }

    public void setFile_id(Long file_id) {
        this.file_id = file_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
