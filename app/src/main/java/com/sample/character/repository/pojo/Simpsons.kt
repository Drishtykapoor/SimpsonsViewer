package com.sample.character.repository.pojo

data class Simpsons(
    val Abstract: String?,
    val AbstractSource: String?,
    val AbstractText: String?,
    val AbstractURL: String?,
    val Answer: String?,
    val AnswerType: String?,
    val Definition: String?,
    val DefinitionSource: String?,
    val DefinitionURL: String?,
    val Entity: String?,
    val Heading: String?,
    val Image: String?,
    val Infobox: String?,
    val Redirect: String?,
    val RelatedTopics: List<RelatedTopic>,
    val Results: List<Any>,
    val Type: String?,
    val meta: Meta?
)