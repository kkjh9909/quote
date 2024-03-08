package com.example.quote.controller

import com.example.quote.service.AuthorService
import com.example.quote.service.CategoryService
import com.example.quote.service.QuoteService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Controller
class QuoteController(
   private val quoteService: QuoteService,
   private val authorService: AuthorService,
   private val categoryService: CategoryService
) {

    @GetMapping("/add-quote")
    fun addQuotePage(model: Model): String {
        val authors = authorService.getAllAuthors()
        val categories = categoryService.getAllCategories()

        model.addAttribute("authors", authors)
        model.addAttribute("categories", categories)

        return "quote-add"
    }

    @PostMapping("/add-quote")
    fun addQuote(
        @RequestBody quote: QuoteAdd
    ): String {
        quoteService.addQuote(quote.categoryId, quote.authorId, quote.content);

        return "quote-add"
    }

    @GetMapping("/search")
    fun searchQuote(@RequestParam query: String,
                    @PageableDefault(size = 10) pageable: Pageable,
                    model: Model
    ): String {

        val response = quoteService.searchQuote(query, pageable)
        if(response.totalPages <= pageable.pageNumber)
            return "redirect:/search?page=${response.totalPages}&query=${query}"


        model.addAttribute("quotes", response.quoteSummaryList)
        model.addAttribute("totalPages", response.totalPages)
        model.addAttribute("currentPage", pageable.pageNumber + 1)
        model.addAttribute("query", query)
        model.addAttribute("count", response.count)

        return "quotes-search-page"
    }

    @GetMapping("/quote/{quoteId}")
    fun getQuoteDetail(
            @PathVariable("quoteId") quoteId: String,
            model: Model
        ): String {
        val quote = quoteService.getQuoteDetail(quoteId)
        val relatedQuotes = quoteService.getRelatedQuote(quoteId)

        model.addAttribute("quote", quote)
        model.addAttribute("relatedQuotes", relatedQuotes)

        return "quote-page"
    }
}

data class QuoteAdd(
    val categoryId: String,
    val authorId: String,
    val content: String
)

data class Query(val query: String)