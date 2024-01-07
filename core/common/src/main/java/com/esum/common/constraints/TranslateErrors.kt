package com.esum.common.constraints

enum class TranslateErrors(val message: String) {
    ResponseIsEmpty("ResponseIsEmpty"),
    VpnProblem("HTTP 451 "),
    ErrorInRequest("ResponseIsEmpty");


}

fun getTranslateErrorByMessage(message: String): TranslateErrors {
    return TranslateErrors.entries.find { it.message == message } ?: TranslateErrors.ErrorInRequest
}
