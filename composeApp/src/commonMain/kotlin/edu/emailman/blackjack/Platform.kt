package edu.emailman.blackjack

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun exitApplication()