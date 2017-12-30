package moj.memes.base.network.parsing

import com.google.gson.TypeAdapterFactory
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory

@GsonTypeAdapterFactory
abstract class AutoValueGsonFactory : TypeAdapterFactory {

    companion object {
        fun create(): TypeAdapterFactory = AutoValueGson_AutoValueGsonFactory()
    }
}