package com.sample.simpsonsviewer.viewmodel

sealed class SortOrder {
    object Ascending: SortOrder()
    object Descending: SortOrder()
}
