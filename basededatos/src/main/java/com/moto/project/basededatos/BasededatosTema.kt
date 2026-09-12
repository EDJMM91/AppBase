package com.moto.project.basededatos

import com.moto.project.theme.Primary
import com.moto.project.theme.Secondary
import com.moto.project.theme.Background
import com.moto.project.theme.Surface

fun getThemeColors(): Map<String, Long> {
    return mapOf(
        "primary" to Primary.toArgb().toLong(),
        "secondary" to Secondary.toArgb().toLong(),
        "background" to Background.toArgb().toLong(),
        "surface" to Surface.toArgb().toLong()
    )
}

data class TemaCollection(
    val primary: Long,
    val secondary: Long,
    val background: Long,
    val surface: Long
)
