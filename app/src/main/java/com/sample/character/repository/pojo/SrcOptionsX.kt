package com.sample.character.repository.pojo

data class SrcOptionsX(
    val directory: String,
    val is_fanon: Int,
    val is_mediawiki: Int,
    val is_wikipedia: Int,
    val language: String,
    val min_abstract_length: Any,
    val skip_abstract: Int,
    val skip_abstract_paren: Int,
    val skip_icon: Int,
    val skip_image_name: Int,
    val skip_qr: String,
    val src_info: String,
    val src_skip: String
)