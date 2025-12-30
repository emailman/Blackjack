package edu.emailman.blackjack

class JsPlatform: Platform {
    override val name: String = "Web with Kotlin/JS"
}

actual fun getPlatform(): Platform = JsPlatform()

actual fun exitApplication() {
    // Browser windows can't be closed programmatically
}