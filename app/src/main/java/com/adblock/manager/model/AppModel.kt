package com.adblock.manager.model

data class AppModel(
    val appName: String,
    val packageName: String,
    var isSelected: Boolean = false
)
