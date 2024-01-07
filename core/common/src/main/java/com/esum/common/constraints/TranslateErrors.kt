package com.esum.common.constraints

enum class TranslateErrors(val message: String) {
    ResponseIsEmpty("ResponseIsEmpty"),
    ErrorInRequest("ResponseIsEmpty");


    companion object {
        fun getTranslateErrorByMessage(message: String): TranslateErrors {
            return entries.toTypedArray().firstOrNull{it.message == message} ?: ErrorInRequest
        }
    }


}