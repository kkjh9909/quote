package com.example.quote.controller

import com.example.quote.service.PostService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PostController(
    private val postService: PostService
) {

    @GetMapping("/posts")
    fun getPosts(@PageableDefault(sort = ["updatedAt"], direction = Sort.Direction.ASC) pageable: Pageable,
                 model: Model
    ): String {
        val posts = postService.getPosts(pageable)

        model.addAttribute("posts", posts)

        return "posts-page"
    }
}