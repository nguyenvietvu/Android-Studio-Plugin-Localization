package com.mrdev.plugin.localization.api

import com.google.gson.Gson
import com.mrdev.plugin.localization.config.C
import com.mrdev.plugin.localization.model.Translation
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

object Translate {
    fun make(text: String, src: String, des: String): String{
        val client = OkHttpClient()
        val formBody: RequestBody = FormBody.Builder()
            .add("q", text)
            .add("target", des)
            .add("source", src)
            .build()

        val request = Request.Builder()
            .url(C.API_URL)
            .header("content-type", "application/x-www-form-urlencoded")
            .addHeader("accept-encoding", "application/gzip")
            .addHeader("x-rapidapi-host", C.RAPID_HOST)
            .addHeader("x-rapidapi-key", C.RAPID_KEY)
            .post(formBody)
            .build()
        val call = client.newCall(request)
        val response = call.execute()
        val test = response.body?.string()
        val gson = Gson()
        val translation: Translation = gson.fromJson(test, Translation::class.java)
        return if(translation.data != null) {
            if (translation.data!!.translations?.count() > 0) {
                translation.data?.translations[0].translatedText
            } else {
                text
            }
        } else {
            text
        }
    }
}