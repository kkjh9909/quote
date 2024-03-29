package com.example.quote.controller

import com.example.quote.service.AuthorService
import com.example.quote.service.QuoteService
import org.springframework.data.domain.Pageable
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
        val response = authorService.getAuthorList(pageable, order)

        if(response.totalPages <= pageable.pageNumber)
            return "redirect:/authors?page=${response.totalPages}"

        model.addAttribute("totalPages", response.totalPages)
        model.addAttribute("authors", response.authorList)
        model.addAttribute("currentPage", pageable.pageNumber + 1)
        model.addAttribute("sort", order)

        return "authors-page"
    }

    @GetMapping("/author/{authorId}")
    fun getAuthorQuotesPage(model: Model,
                            @PageableDefault(size = 10) pageable: Pageable,
                            @PathVariable authorId: String
    ): String  {
        val quotesResponse = quoteService.getQuotesByAuthor(authorId, pageable)
        val author = authorService.getAuthorName(authorId)

        if(quotesResponse.totalPages < pageable.pageNumber)
            return "redirect:/author/${authorId}?page=${quotesResponse.totalPages}"

        model.addAttribute("quotes", quotesResponse.quoteSummaryList)
        model.addAttribute("currentPage", pageable.pageNumber + 1)
        model.addAttribute("totalPages", quotesResponse.totalPages)
        model.addAttribute("count", quotesResponse.count)
        model.addAttribute("authorId", authorId)
        model.addAttribute("author", author)

        return "author-quotes-page"
    }
}