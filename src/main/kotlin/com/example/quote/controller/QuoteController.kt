package com.example.quote.controller

import com.example.quote.service.AuthorService
import com.example.quote.service.CategoryService
import com.example.quote.service.QuoteService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
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
}

data class QuoteAdd(
    val categoryId: String,
    val authorId: String,
    val content: String
)