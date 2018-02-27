package moj.memes.list.domain

import moj.memes.base.network.model.MemesDto
import moj.memes.base.model.Meme
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun map(dto: MemesDto): List<Meme> {
        return dto.data.memes.map {
            Meme(
                    name = it.name,
                    imageUrl = it.imageUrl,
                    imageWidth = it.imageWidth,
                    imageHeight = it.imageHeight
            )
        }
    }
}
