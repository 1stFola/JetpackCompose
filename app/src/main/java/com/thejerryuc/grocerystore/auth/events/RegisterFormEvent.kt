package com.thejerryuc.grocerystore.auth.events

sealed class RegisterFormEvent {
    data class EnteredEmail(val value: String) : RegisterFormEvent()
    data class EnteredPassword(val value: String) : RegisterFormEvent()
    data class EnteredFirstname(val value: String) : RegisterFormEvent()
    data class EnteredLastname(val value: String) : RegisterFormEvent()
    data class FocusChange(val focusFieldName: String) : RegisterFormEvent()

}
