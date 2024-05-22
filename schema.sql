-- Create database if not exists
CREATE DATABASE IF NOT EXISTS testdb;
USE testdb;

-- Create KPac table
CREATE TABLE kpac (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      description VARCHAR(2000),
                      creation_date VARCHAR(10) NOT NULL
);

-- Create KPacSet table
CREATE TABLE kpac_set (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL
);

-- Create KPacSet_KPac (join table)
CREATE TABLE kpac_set_kpac (
                               kpac_set_id BIGINT,
                               kpac_id BIGINT,
                               PRIMARY KEY (kpac_set_id, kpac_id),
                               FOREIGN KEY (kpac_set_id) REFERENCES kpac_set(id),
                               FOREIGN KEY (kpac_id) REFERENCES kpac(id)
);