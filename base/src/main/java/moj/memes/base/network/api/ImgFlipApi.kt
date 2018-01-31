package moj.memes.base.network.api

import io.reactivex.Single
import moj.memes.base.network.model.MemesDto
import retrofit2.http.GET

interface ImgFlipApi {

    @GET("get_memes")
    fun fetchMemes(): Single<MemesDto>
}
