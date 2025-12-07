package com.adblock.manager.ui

data class AppInfo(
    val appName: String,
    val packageName: String,
    val icon: android.graphics.drawable.Drawable,
    var isSelected: Boolean = false
)
