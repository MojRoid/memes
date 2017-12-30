package moj.memes.base.network.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Gone with a quick and simple approach using generated type adapters (which works faster
 * than reflection based GSON serialization).
 * <p>
 * Could have also manually parsed the response in a traditional type adapter:
 * - Pros: have fine grain control during parsing, useful for flattening responses into
 * models that could be used throughout all layers + it's fast.
 * - Cons: Needs testing, and slightly more time consuming to maintain.
 */
@AutoValue
public abstract class MemesDto {


    @SerializedName("success")
    public abstract boolean isSuccess();

    @SerializedName("data")
    public abstract Memes getData();

    public static TypeAdapter<MemesDto> typeAdapter(Gson gson) {
        return new AutoValue_MemesDto.GsonTypeAdapter(gson);
    }

    @AutoValue
    public static abstract class Memes {

        @SerializedName("memes")
        public abstract List<Meme> getMemes();

        public static TypeAdapter<Memes> typeAdapter(Gson gson) {
            return new AutoValue_MemesDto_Memes.GsonTypeAdapter(gson);
        }
    }

    @AutoValue
    public static abstract class Meme {

        @SerializedName("id")
        public abstract String getId();

        @SerializedName("name")
        public abstract String getName();

        @SerializedName("url")
        public abstract String getImageUrl();

        @SerializedName("height")
        public abstract int getImageHeight();

        @SerializedName("width")
        public abstract int getImageWidth();

        public static TypeAdapter<Meme> typeAdapter(Gson gson) {
            return new AutoValue_MemesDto_Meme.GsonTypeAdapter(gson);
        }
    }
}