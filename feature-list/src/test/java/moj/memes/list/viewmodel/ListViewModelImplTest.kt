package moj.memes.list.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import moj.memes.list.domain.FetchMemesUseCase
import moj.memes.base.model.Meme
import moj.memes.list.viewmodel.ListViewModel.State
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class ListViewModelImplTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    @Mock private lateinit var fetchMemesUseCase: FetchMemesUseCase
    @Mock private lateinit var memes: List<Meme>
    @Mock private lateinit var observer: Observer<State>

    private lateinit var subject: ListViewModelImpl

    private val state: MediatorLiveData<State> = MediatorLiveData()
    private val fetchMemesUseCaseLiveData: MutableLiveData<FetchMemesUseCase.Result> = MutableLiveData()

    @Before
    fun setUp() {
        setUpLiveData()
        subject = ListViewModelImpl(state, fetchMemesUseCase)
    }

    private fun setUpLiveData() {
        state.observeForever(observer)
        given(fetchMemesUseCase.getLiveData()).willReturn(fetchMemesUseCaseLiveData)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `fetchMemes - execute`() {
        whenMemesAreFetched()
        thenObserverShouldReceiveCorrectStates(State.ShowLoading)
        thenUseCaseShouldBeExecuted()
        thenUseCaseShouldHaveNoMoreInteractions()
    }

    @Test
    fun `fetchMemes - success`() {
        whenFetchedMemesHasResult(FetchMemesUseCase.Result.OnSuccess(memes))
        thenObserverShouldReceiveCorrectStates(State.MemesLoaded(memes), State.ShowContent)
    }

    @Test
    fun `fetchMemes - error`() {
        whenFetchedMemesHasResult(FetchMemesUseCase.Result.OnError)
        thenObserverShouldReceiveCorrectStates(State.ShowError)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenMemesAreFetched() {
        subject.fetchMemes()
    }

    private fun whenFetchedMemesHasResult(result: FetchMemesUseCase.Result) {
        fetchMemesUseCaseLiveData.value = result
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenUseCaseShouldBeExecuted() {
        then(fetchMemesUseCase).should().execute()
    }

    private fun thenUseCaseShouldHaveNoMoreInteractions() {
        then(fetchMemesUseCase).should().getLiveData()
        then(fetchMemesUseCase).shouldHaveNoMoreInteractions()
    }

    private fun thenObserverShouldReceiveCorrectStates(vararg expected: State) {
        expected.forEach { then(observer).should().onChanged(it) }
        then(observer).shouldHaveNoMoreInteractions()
    }
}
