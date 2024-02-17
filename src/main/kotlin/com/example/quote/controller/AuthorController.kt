package com.example.quote.controller

import com.example.quote.service.AuthorService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AuthorController(
    private val authorService: AuthorService
) {

    @GetMapping("/authors")
    fun getCategoriesPage(model: Model,
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
}