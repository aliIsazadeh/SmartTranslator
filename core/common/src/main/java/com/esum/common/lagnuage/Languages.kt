package com.esum.common.lagnuage

import com.esum.common.constraints.TranslateErrors

enum class Languages(val key : String) {

    English("en"),
    Farsi("fa"),
    Italian("it"),
    French("fr"),
    Arabic("ar"),
    Japans("ja"),
    Germany("ge"),
    Turkish("tu"),
    Spanish("sp")

}

fun getLanguagesByKey(key: String): Languages {
    return Languages.entries.find { it.key == key } ?: Languages.Farsi
}