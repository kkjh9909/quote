package com.example.quote

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuoteApplication

fun main(args: Array<String>) {
    runApplication<QuoteApplication>(*args)
}
