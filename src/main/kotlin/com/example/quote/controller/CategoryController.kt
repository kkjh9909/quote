package com.example.quote.controller

import com.example.quote.service.CategoryService
import com.example.quote.service.QuoteService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class CategoryController(
    private val categoryService: CategoryService,
    private val quoteService: QuoteService
) {

    @GetMapping("/category/{categoryId}")
    fun getCategoryPage(@PathVariable categoryId: String,
                        @PageableDefault(sort = ["content"], direction = Sort.Direction.ASC) pageable: Pageable,
                        model: Model): String {

        val category = categoryService.getCategoryName(categoryId);
        val quoteSummary = quoteService.getQuoteList(categoryId, pageable)

        model.addAttribute("category", category)
        model.addAttribute("quotes", quoteSummary.quoteSummaryList)
        model.addAttribute("totalPages", quoteSummary.totalPages)

        model.addAttribute("currentPage", pageable.pageNumber)

        return "quotes-page"
    }

    @GetMapping("/categories")
    fun getCategoriesPage(model: Model, pageable: Pageable): String {
        val categories = categoryService.getCategoryList(pageable)

        model.addAttribute("totalPages", categories.totalPages)
        model.addAttribute("categories", categories.categoryList)
        model.addAttribute("currentPage", pageable.pageNumber)

        return "categories-page"
    }
}