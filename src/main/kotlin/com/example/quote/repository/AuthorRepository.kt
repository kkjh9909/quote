package com.example.quote.repository

import com.example.quote.entity.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository: JpaRepository<Author, String> {

}