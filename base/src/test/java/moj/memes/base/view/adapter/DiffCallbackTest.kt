package moj.memes.base.view.adapter

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import java.util.*

@Suppress("IllegalIdentifier")
class DiffCallbackTest {

    private data class Example(val text: String, val number: Int, private val flag: Boolean)

    private val example1 = Example("example 1", 5, true)
    private val example2 = Example("example 2", 5, true)
    private val example3 = Example("example 3", 8, false)

    private lateinit var subject: DiffCallback
    private var oldList: MutableList<Example> = mutableListOf()
    private var newList: MutableList<Example> = mutableListOf()

    @Before
    fun setUp() {
        subject = DiffCallback()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `setData - empty lists`() {
        givenOldList(emptyList())
        givenNewList(emptyList())
        whenListsAreSet()
        thenListSizesAreReportedAccurately()
        thenItemsEqualityIsReportedAccurately(false)
        thenContentsEqualityIsReportedAccurately(false)
    }

    @Test
    fun `setData - empty old list`() {
        givenOldList(emptyList())
        givenNewList(listOf(example1, example2))
        whenListsAreSet()
        thenListSizesAreReportedAccurately()
        thenItemsEqualityIsReportedAccurately(false, false)
        thenContentsEqualityIsReportedAccurately(false, false)
    }

    @Test
    fun `setData - empty new list`() {
        givenOldList(listOf(example1, example2))
        givenNewList(emptyList())
        whenListsAreSet()
        thenListSizesAreReportedAccurately()
        thenItemsEqualityIsReportedAccurately(false, false)
        thenContentsEqualityIsReportedAccurately(false, false)
    }

    @Test
    fun `setData - equal`() {
        givenOldList(listOf(example1, example2))
        givenNewList(listOf(example1, example2))
        whenListsAreSet()
        thenListSizesAreReportedAccurately()
        thenItemsEqualityIsReportedAccurately(true, true)
        thenContentsEqualityIsReportedAccurately(true, true)
    }

    @Test
    fun `setData - equal with varied size`() {
        givenOldList(listOf(example1, example2))
        givenNewList(listOf(example1, example2, example1, example1))
        whenListsAreSet()
        thenListSizesAreReportedAccurately()
        thenItemsEqualityIsReportedAccurately(true, true, false, false)
        thenContentsEqualityIsReportedAccurately(true, true, false, false)
    }

    @Test
    fun `setData - partially equal`() {
        givenOldList(listOf(example1, example2, example1, example1, example2, example1))
        givenNewList(listOf(example2, example2, example1, example2, example3, example3))
        whenListsAreSet()
        thenListSizesAreReportedAccurately()
        thenItemsEqualityIsReportedAccurately(false, true, true, false, false, false)
        thenContentsEqualityIsReportedAccurately(false, true, true, false, false, false)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // GIVEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun givenOldList(list: List<Example>) {
        oldList = ArrayList()
        oldList.addAll(list)
    }

    private fun givenNewList(list: List<Example>) {
        newList = ArrayList()
        newList.addAll(list)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenListsAreSet() {
        subject.setLists(oldList, newList)
    }

   ////////////////////////////////////////////////////////////////////////////////////////////////
   // THEN                                                                                       //
   ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenListSizesAreReportedAccurately() {
        assertThat(subject.oldListSize).isEqualTo(oldList.size)
        assertThat(subject.newListSize).isEqualTo(newList.size)
    }

    private fun thenItemsEqualityIsReportedAccurately(vararg booleans: Boolean) {
        for (i in booleans.indices) {
            assertThat(subject.areItemsTheSame(i, i)).isEqualTo(booleans[i])
        }
    }

    private fun thenContentsEqualityIsReportedAccurately(vararg booleans: Boolean) {
        for (i in booleans.indices) {
            assertThat(subject.areContentsTheSame(i, i)).isEqualTo(booleans[i])
        }
    }
}
