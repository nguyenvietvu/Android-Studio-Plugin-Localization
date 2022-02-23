package com.mrdev.plugin.localization.extension

import com.mrdev.plugin.localization.helper.Loc

val String.key: String
    get() {
        var t = this.toLowerCase().trim()
        if (t[0].isDigit()) {
            t = "key_$t"
        }

        val regex = Regex("[^A-Za-z0-9_]")
        t = t.replace(" ", "_")
        return regex.replace(t, "")
    }

val String.l: String
get(){
    return Loc.textOf(this)
}