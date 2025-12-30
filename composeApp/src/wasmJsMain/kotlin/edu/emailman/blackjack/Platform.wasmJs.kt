package edu.emailman.blackjack

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

actual fun exitApplication() {
    // Browser windows can't be closed programmatically
}