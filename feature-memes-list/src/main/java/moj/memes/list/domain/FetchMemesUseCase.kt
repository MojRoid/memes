package moj.memes.list.domain

import moj.memes.base.domain.UseCase
import moj.memes.list.domain.FetchMemesUseCase.Result
import moj.memes.base.model.Meme

interface FetchMemesUseCase : UseCase<Result> {

    sealed class Result {
        data class OnSuccess(val memes: List<Meme>) : Result()
        object OnError : Result()
    }

    fun execute()
}
