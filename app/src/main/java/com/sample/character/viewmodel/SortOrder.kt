package com.sample.character.viewmodel

sealed class SortOrder {
    object Ascending: SortOrder()
    object Descending: SortOrder()
}
