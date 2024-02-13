package com.example.quote.repository

import com.example.quote.entity.Category
import com.example.quote.entity.Quote
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface QuoteRepository: JpaRepository<Quote, String> {
    fun findByCategory(category: Category, pageable: Pageable): Page<Quote>
}