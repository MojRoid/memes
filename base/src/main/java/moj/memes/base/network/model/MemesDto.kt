package moj.memes.base.network.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

/**
 * Gone with a quick and simple approach using generated type adapters (which works faster
 * than reflection based GSON serialization).
 * <p>
 * Could have also manually parsed the response in a traditional type adapter:
 * - Pros: have fine grain control during parsing, useful for flattening responses into
 * models that could be used throughout all layers + it's fast.
 * - Cons: A single model used across layers not always a good idea, needs testing,
 * slightly more time consuming to maintain.
 */

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