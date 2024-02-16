package com.example.quote.repository

import com.example.quote.entity.Category
import com.example.quote.entity.Quote
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface CategoryRepository: JpaRepository<Category, String> {

    fun findAllByOrderByName(pageable: Pageable): Page<Category>
    fun findAllByOrderByQuoteCountDesc(pageable: Pageable): Page<Category>
}