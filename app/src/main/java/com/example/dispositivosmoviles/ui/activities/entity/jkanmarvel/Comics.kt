package com.example.dispositivosmoviles.ui.activities.entity.jkanmarvel

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)