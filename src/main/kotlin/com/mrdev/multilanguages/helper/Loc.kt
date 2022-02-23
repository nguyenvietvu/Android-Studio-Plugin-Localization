package com.mrdev.multilanguages.helper

import java.util.*

object Loc {
    private val bundle = ResourceBundle.getBundle("Localization")
    fun textOf(key: String): String {
        return bundle.getString(key)
    }
}