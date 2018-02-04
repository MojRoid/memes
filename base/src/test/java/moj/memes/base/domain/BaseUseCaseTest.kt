package moj.memes.base.domain

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BaseUseCaseTest {

    class TestUseCase(compositeDisposable: CompositeDisposable) : BaseUseCase<String>(compositeDisposable) {
        fun trackDisposable(disposable: Disposable) {
            disposable.track()
        }
    }

    @Mock private lateinit var disposable1: Disposable
    @Mock private lateinit var disposable2: Disposable
    @Mock private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var subject: TestUseCase

    @Before
    fun setUp() {
        subject = TestUseCase(compositeDisposable)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun trackDisposable() {
        whenDisposablesAreTracked()
        thenDisposablesShouldBeAddedToTheCompositeDisposable()
    }

    @Test
    fun cleanUp() {
        whenUseCasesAreCleanedUp()
        thenTheCompositeDisposableShouldBeCleared()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenDisposablesAreTracked() {
        subject.trackDisposable(disposable1)
        subject.trackDisposable(disposable2)
    }

    private fun whenUseCasesAreCleanedUp() {
        subject.cleanUp()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenDisposablesShouldBeAddedToTheCompositeDisposable() {
        then(compositeDisposable).should().add(disposable1)
        then(compositeDisposable).should().add(disposable2)
        then(compositeDisposable).shouldHaveNoMoreInteractions()
    }

    private fun thenTheCompositeDisposableShouldBeCleared() {
        then(compositeDisposable).should().clear()
        then(compositeDisposable).shouldHaveNoMoreInteractions()
    }
}
