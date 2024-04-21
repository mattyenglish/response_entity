package com.project.responseEntity.model;

import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "Books")
@Entity
public class Book {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int bookId;
private String bookName;
private Integer bookPrice;
private String bookAuthor;
private String language;
private Integer pages;
private String description;



}
