package com.example.quote.service

import com.example.quote.entity.Quote
import com.example.quote.repository.AuthorRepository
import com.example.quote.repository.CategoryRepository
import com.example.quote.repository.QuoteRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    @Transactional
    fun addQuote(categoryId: String, authorId: String, content: String) {
        val category = categoryRepository.findById(categoryId)
        val author = authorRepository.findById(authorId)

        val quote = Quote.create(category.get(), author.get(), content)
        category.get().quoteCount++;
        author.get().quoteCount++;

        quoteRepository.save(quote)
    }

    fun getTodayQuote(): QuoteDto {
        val max = quoteRepository.count()
        val index = (0 until max).random().toInt()

        val quote = quoteRepository.findAll(PageRequest.of(index, 1))
        return QuoteDto(quote.content[0].content, quote.content[0].author.name, quote.content[0].author.photo)
    }

    fun getLatestQuotes(): List<LatestQuote> {
        val quotes = quoteRepository.findByOrderByUpdatedAtDesc(PageRequest.of(0, 10)).content

        return quotes.map { quote -> LatestQuote(quote.id, quote.content) }
    }
}

data class QuoteSummary(val totalPages: Int, val quoteSummaryList: List<QuoteSummaryList>)

data class QuoteSummaryList(val id: String, val content: String?, val author: String?)

data class QuoteDto(val content: String, val author: String, val photo: String)

data class LatestQuote(val id: String, val content: String)