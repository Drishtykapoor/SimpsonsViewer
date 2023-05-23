package com.sample.character.repository.pojo

data class NetworkData(
    val Abstract: String,
    val AbstractSource: String,
    val AbstractText: String,
    val AbstractURL: String,
    val Answer: String,
    val AnswerType: String,
    val Definition: String,
    val DefinitionSource: String,
    val DefinitionURL: String,
    val Entity: String,
    val Heading: String,
    val Image: String,
    val ImageHeight: String,
    val ImageIsLogo: String,
    val ImageWidth: String,
    val Infobox: String,
    val Redirect: String,
    val RelatedTopics: List<Any>,
    val Results: List<Any>,
    val Type: String,
    val meta: MetaX
)