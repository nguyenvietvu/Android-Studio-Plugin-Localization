# Android-Studio-Plugin-Localization
Android Studio Plugin Localization.

Marketplace: https://plugins.jetbrains.com/plugin/18675-localization

Generate (key,value) form text and insert into strings.xml.

Translate text into multiple languages that project supported

video demo: 
[![Watch the video](https://img.youtube.com/vi/qWnkZrnag_c/maxresdefault.jpg)](https://youtu.be/qWnkZrnag_c)

#Config
1. Translate api:
   
    from: https://rapidapi.com/googlecloud/api/google-translate1
    
    source: /config/C.kt

        val API_URL = "your url"
        val RAPID_HOST = "your rapid host"
        val RAPID_KEY = "your rapid key"
2. Publish plugin:

    source: build.gradle
   
        publishPlugin {
            channels = ['your channel']
            token = 'your token'
        }
