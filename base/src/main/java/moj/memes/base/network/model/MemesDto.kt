package moj.memes.base.network.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class MemesDto(
        @Json(name = "success") val success: Boolean,
        @Json(name = "data") val data: Memes
)

@JsonSerializable
data class Memes(
        @Json(name = "memes") val memes: List<Meme>
)

@JsonSerializable
data class Meme(
        @Json(name = "id") val id: String,
        @Json(name = "name") val name: String,
        @Json(name = "url") val imageUrl: String,
        @Json(name = "height") val imageHeight: Int,
        @Json(name = "width") val imageWidth: Int
)
