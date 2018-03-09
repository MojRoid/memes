package moj.memes.list.repository

import io.reactivex.Single
import moj.memes.base.network.model.MemesDto

interface MemesRepository {

    fun fetchMemes(): Single<MemesDto>
}
