package com.example.quote.controller

import com.example.quote.service.AuthorService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthorController(
    private val authorService: AuthorService
) {

    @GetMapping("/authors")
    fun getCategoriesPage(model: Model, pageable: Pageable): String {
        val authors = authorService.getAuthorList(pageable)

        model.addAttribute("totalPages", authors.totalPages)
        model.addAttribute("authors", authors.authorList)
        model.addAttribute("currentPage", pageable.pageNumber)

        return "authors-page"
    }
}