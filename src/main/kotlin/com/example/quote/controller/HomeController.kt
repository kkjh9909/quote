package com.example.quote.controller

import com.example.quote.service.AuthorService
import com.example.quote.service.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController(
    private val authorService: AuthorService,
    private val categoryService: CategoryService
) {

    @GetMapping("/")
    fun home(model: Model): String {
        val authors = authorService.getAuthors()
        val categories = categoryService.getCategories()

        model.addAttribute("authors", authors)
        model.addAttribute("categories", categories)
        return "index";
    }
}