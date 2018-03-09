package moj.memes.list.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import io.reactivex.Single
import moj.memes.base.network.model.MemesDto
import moj.memes.list.domain.FetchMemesUseCase.Result
import moj.memes.base.model.Meme
import moj.memes.list.repository.MemesRepository
import moj.memes.base.test.rule.SchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class FetchMemesUseCaseImplTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()
    @Rule
    @JvmField
    val schedulerRule = SchedulerRule()

    @Mock private lateinit var repository: MemesRepository
    @Mock private lateinit var mapper: Mapper
    @Mock private lateinit var dto: MemesDto
    @Mock private lateinit var throwable: Throwable
    @Mock private lateinit var memes: List<Meme>

    private lateinit var subject: FetchMemesUseCaseImpl

    @Before
    fun setUp() {
        subject = FetchMemesUseCaseImpl(repository, mapper)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `execute - success`() {
        givenRepository(Single.just(dto))
        givenMapping(memes)
        whenUseCaseIsExecuted()
        thenLiveDataShouldHaveCorrectValue(Result.OnSuccess(memes))
    }

    @Test
    fun `execute - error`() {
        givenRepository(Single.error(throwable))
        whenUseCaseIsExecuted()
        thenLiveDataShouldHaveCorrectValue(Result.OnError)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // GIVEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun givenRepository(single: Single<MemesDto>) {
        given(repository.fetchMemes()).willReturn(single)
    }

    private fun givenMapping(memes: List<Meme>) {
        given(mapper.map(dto)).willReturn(memes)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenUseCaseIsExecuted() {
        subject.execute()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenLiveDataShouldHaveCorrectValue(result: Result) {
        assertThat(subject.getLiveData().value).isEqualTo(result)
    }
}
