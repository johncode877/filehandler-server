/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  John Sevillano <john.sevillano@gmail.com>
 * Created: 9 may. 2025
 */

CREATE TABLE file_details(    
    file_id INT NOT NULL,
    id VARCHAR(36) NOT NULL,    
    name VARCHAR(50) NOT NULL,    
    date TIMESTAMP NOT NULL,    
    hash VARCHAR(64) NOT NULL,
    size INT NOT NULL,
    status INT DEFAULT 1,
    PRIMARY KEY(id)
);


CREATE SEQUENCE file_details_seq
START 1
INCREMENT 1
MINVALUE 1
OWNED BY file_details.file_id;

