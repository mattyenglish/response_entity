package com.project.responseEntity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.responseEntity.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{

}
