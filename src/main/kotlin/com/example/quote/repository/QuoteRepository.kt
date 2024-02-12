package com.example.quote.repository

import com.example.quote.entity.Quote
import org.springframework.data.jpa.repository.JpaRepository

interface QuoteRepository: JpaRepository<Quote, String> {

}