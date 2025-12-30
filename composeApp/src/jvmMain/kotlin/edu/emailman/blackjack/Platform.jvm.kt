package edu.emailman.blackjack

import kotlin.system.exitProcess

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun exitApplication() {
    exitProcess(0)
}