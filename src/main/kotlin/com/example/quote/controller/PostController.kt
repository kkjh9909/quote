package com.example.quote.controller

import com.example.quote.service.PostService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class PostController(
    private val postService: PostService
) {

    @GetMapping("/posts")
    fun getPosts(@PageableDefault(sort = ["updatedAt"], direction = Sort.Direction.DESC, size = 10) pageable: Pageable,
                 model: Model
    ): String {
        val response = postService.getPosts(pageable)

        if(response.totalPages < pageable.pageNumber)
            return "redirect:/posts?page=${response.totalPages}"

        model.addAttribute("posts", response.posts)
        model.addAttribute("totalPages", response.totalPages)
        model.addAttribute("currentPage", pageable.pageNumber + 1)

        return "posts-page"
    }

    @GetMapping("/post/write")
    fun getPostWritePage(): String {
        return "post-write"
    }

    @PostMapping("/post/write")
    @ResponseStatus(value = HttpStatus.OK)
    fun writePost(@RequestBody writePostRequest: WritePostRequest, request: HttpServletRequest) {
        val clientIp = getClientIp(request)

        postService.writePost(writePostRequest, clientIp)
    }


    private fun getClientIp(request: HttpServletRequest): String {
        val forwardedForHeader = request.getHeader("X-Forwarded-For")
        return if (forwardedForHeader != null && forwardedForHeader.isNotEmpty()) {
            forwardedForHeader.split(",").first().trim()
        } else {
            request.remoteAddr
        }
    }

    @GetMapping("/post/{postId}")
    fun getPostPage(@PathVariable postId: String, model: Model): String {
        val response = postService.getPost(postId)
        val recentPostsResponse = postService.getRecentPosts()

        model.addAttribute("postDetails", response)
        model.addAttribute("recentPosts", recentPostsResponse)

        return "post-page"
    }
}

data class WritePostRequest(val writer: String, val password: String, val title: String, val content: String)