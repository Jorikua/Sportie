package com.example.sportie.utils

data class Selectable<T: Any>(
    val item: T,
    val isSelected: Boolean
)