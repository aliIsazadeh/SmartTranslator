package com.esum.common.constraints

enum class TranslateErrors(val message: String) {
    ResponseIsEmpty("ResponseIsEmpty"),
    ErrorInRequest("ResponseIsEmpty");


    fun getTranslateErrorByMessage(message: String): TranslateErrors {
        return entries.toTypedArray().firstOrNull{it.message == message} ?: ErrorInRequest
    }

}