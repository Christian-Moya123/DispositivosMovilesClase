package com.example.dispositivosmoviles.ui.activities.entity.jkan

data class Pagination(
    val current_page: Int,
    val has_next_page: Boolean,
    val items: Items,
    val last_visible_page: Int
)