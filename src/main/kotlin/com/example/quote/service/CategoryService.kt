package com.example.quote.service

import com.example.quote.entity.Category
import com.example.quote.repository.CategoryRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun getCategories(): List<CategoryDto> {
        val categories = categoryRepository.findAll(PageRequest.of(0, 10)).content

        return categories.map { category -> CategoryDto(category.id, category.name) }
    }

    fun getCategoryName(categoryId: String): CategoryInfo? {
        val category = categoryRepository.findById(categoryId)

        return CategoryInfo(category.get().id, category.get().name)
    }

    fun getAllCategories(): List<CategoryDto> {
        return categoryRepository.findAll().map { category -> CategoryDto(category.id, category.name) }
    }

    fun getCategoryList(pageable: Pageable, order: String): CategoryList {
        val categories: Page<Category>

        if(order == "popular")
            categories = categoryRepository.findAllByOrderByQuoteCountDesc(pageable)
        else
            categories = categoryRepository.findAllByOrderByName(pageable)

        return CategoryList(categories.totalPages, categories.content.map {category -> CategoryDtoWithCount(category.id, category.name, category.quoteCount) })
    }
}

data class CategoryInfo(val id: String, val name: String?)
data class CategoryDto(val id: String, val category: String)

data class CategoryDtoWithCount(val id: String, val category: String, val count: Int)
data class CategoryList(val totalPages: Int, val categoryList: List<CategoryDtoWithCount>)