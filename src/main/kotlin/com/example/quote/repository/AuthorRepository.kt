package com.example.quote.repository

import com.example.quote.entity.Author
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository: JpaRepository<Author, String> {
    fun findAllByOrderByQuoteCountDesc(pageable: Pageable): Page<Author>
    fun findAllByOrderByName(pageable: Pageable): Page<Author>
}