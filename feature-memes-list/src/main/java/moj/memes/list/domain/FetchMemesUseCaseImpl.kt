package moj.memes.list.domain

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moj.memes.base.domain.BaseUseCase
import moj.memes.list.domain.FetchMemesUseCase.Result
import moj.memes.base.model.Meme
import moj.memes.list.repository.MemesRepository
import timber.log.Timber
import javax.inject.Inject

class FetchMemesUseCaseImpl @Inject constructor(
        private val repository: MemesRepository,
        private val mapper: Mapper
) : BaseUseCase<Result>(),
        FetchMemesUseCase {

    override fun execute() {
        repository.fetchMemes()
                .map(mapper::map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::success, ::error)
                .track()
    }

    private fun success(memes: List<Meme>) {
        liveData.value = Result.OnSuccess(memes)
    }

    private fun error(throwable: Throwable) {
        Timber.e(throwable)
        liveData.value = Result.OnError
    }
}
