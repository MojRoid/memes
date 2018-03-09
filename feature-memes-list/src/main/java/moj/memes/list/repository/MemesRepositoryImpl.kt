package moj.memes.list.repository

import io.reactivex.Single
import moj.memes.base.network.api.ImgFlipApi
import moj.memes.base.network.model.MemesDto
import javax.inject.Inject

class MemesRepositoryImpl @Inject constructor(private val api: ImgFlipApi) : MemesRepository {

    override fun fetchMemes(): Single<MemesDto> = api.fetchMemes()
}
