package com.example.quote.service

import com.example.quote.entity.Quote
import com.example.quote.repository.AuthorRepository
import com.example.quote.repository.CategoryRepository
import com.example.quote.repository.QuoteRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class QuoteService(
    private val categoryRepository: CategoryRepository,
    private val authorRepository: AuthorRepository,
    private val quoteRepository: QuoteRepository
) {

    fun getQuoteList(categoryId: String, pageable: Pageable): QuoteSummary {
        val category = categoryRepository.findById(categoryId)

        val quotes: Page<Quote> = quoteRepository.findByCategory(category.get(), pageable)

        return QuoteSummary(quotes.totalPages, quotes.content.map { quote -> QuoteSummaryList(quote.id, quote.content, quote.author?.name) })
    }

    fun addQuote(categoryId: String, authorId: String, content: String) {
        val category = categoryRepository.findById(categoryId)
        val author = authorRepository.findById(authorId)

        val quote = Quote.create(category.get(), author.get(), content)
        quoteRepository.save(quote)
    }
}

data class QuoteSummary(val totalPages: Int, val quoteSummaryList: List<QuoteSummaryList>)

data class QuoteSummaryList(val id: String, val content: String?, val author: String?)