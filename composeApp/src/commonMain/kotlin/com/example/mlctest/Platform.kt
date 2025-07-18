package com.example.mlctest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform