# Android-Studio-Plugin-Multi-Languages
Android Studio Plugin Multi-Languages

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