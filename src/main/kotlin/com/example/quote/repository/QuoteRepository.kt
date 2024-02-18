package com.example.quote.repository

import com.example.quote.entity.Category
import com.example.quote.entity.Quote
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface QuoteRepository: JpaRepository<Quote, String> {
    fun findByCategory(category: Category, pageable: Pageable): Page<Quote>

    fun findByOrderByUpdatedAtDesc(pageable: Pageable): Page<Quote>

    fun findByContentContaining(query: String, pageable: Pageable): Page<Quote>

    fun findTop10ByCategoryOrderByUpdatedAtDesc(category: Category): List<Quote>
}