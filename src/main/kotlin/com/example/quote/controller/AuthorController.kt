package com.example.quote.controller

import com.example.quote.repository.QuoteRepository
import com.example.quote.service.AuthorService
import com.example.quote.service.QuoteService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AuthorController(
    private val authorService: AuthorService,
    private val quoteService: QuoteService
) {

    @GetMapping("/authors")
    fun getAuthorsPage(model: Model,
                          @RequestParam(value = "order", defaultValue = "alpha") order: String,
                          @PageableDefault(size = 10) pageable: Pageable)
    : String {
        val authors = authorService.getAuthorList(pageable, order)

        model.addAttribute("totalPages", authors.totalPages)
        model.addAttribute("authors", authors.authorList)
        model.addAttribute("currentPage", pageable.pageNumber)
        model.addAttribute("sort", order)

        return "authors-page"
    }

    @GetMapping("/author/{authorId}")
    fun getAuthorQuotesPage(model: Model,
                            @PageableDefault(size = 10) pageable: Pageable,
                            @PathVariable authorId: String
    ): String  {
        val quotes = quoteService.getQuotesByAuthor(authorId, pageable)
        val author = authorService.getAuthorName(authorId)

        model.addAttribute("quotes", quotes.quoteSummaryList)
        model.addAttribute("currentPage", pageable.pageNumber)
        model.addAttribute("totalPages", quotes.totalPages)
        model.addAttribute("count", quotes.count)
        model.addAttribute("authorId", authorId)
        model.addAttribute("author", author)

        return "author-quotes-page"
    }
}