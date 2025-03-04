-- Drop existing tables if they exist
/*
DROP TABLE IF EXISTS Furniture;
DROP TABLE IF EXISTS Occupants;
DROP TABLE IF EXISTS Rooms;
DROP TABLE IF EXISTS Blocks;
DROP TABLE IF EXISTS Users;

 */


-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'STAFF') NOT NULL DEFAULT 'STAFF',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE IF NOT EXISTS Blocks (
    blockID INT AUTO_INCREMENT PRIMARY KEY,
    name CHAR(1) UNIQUE NOT NULL CHECK (name in ('A', 'B', 'C', 'D', 'E', 'F', 'G')), -- 'A', 'B', 'C', etc.
    max_rooms INT NOT NULL  -- 40 for A-D, 20 for E-G
);

-- Rooms Table
CREATE TABLE IF NOT EXISTS Rooms (
    roomID INT AUTO_INCREMENT PRIMARY KEY,
    blockID INT NOT NULL,
    room_number VARCHAR(4) UNIQUE NOT NULL,  -- A101, B202, etc.
    floor INT NOT NULL,                      -- Floor number extracted from room_number
    max_occupants INT NOT NULL CHECK (max_occupants IN (1, 2)),
    FOREIGN KEY (blockID) REFERENCES Blocks(blockID) ON DELETE CASCADE
    );

-- Occupants Table
CREATE TABLE IF NOT EXISTS Occupants (
    occupantID INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    id_number VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,
    roomID INT NOT NULL,
    date_added TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (roomID) REFERENCES Rooms(roomID) ON DELETE CASCADE
    );

-- Furniture Table
CREATE TABLE IF NOT EXISTS Furniture (
    furnitureID INT AUTO_INCREMENT PRIMARY KEY,
    roomID INT NOT NULL,
    furniture_type VARCHAR(50) NOT NULL,
    furniture_condition VARCHAR(50) NOT NULL,
    FOREIGN KEY (roomID) REFERENCES Rooms(roomID) ON DELETE CASCADE
    );
