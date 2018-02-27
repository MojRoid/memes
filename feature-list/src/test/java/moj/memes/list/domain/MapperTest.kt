package moj.memes.list.domain

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import moj.memes.base.test.extension.jsonToObject
import moj.memes.base.network.model.MemesDto
import moj.memes.base.network.adapter.ApplicationJsonAdapterFactory
import moj.memes.base.model.Meme
import org.junit.Before
import org.junit.Test

@Suppress("IllegalIdentifier")
class MapperTest {

    private lateinit var subject: Mapper

    private val moshi: Moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()
    private lateinit var dto: MemesDto
    private var actual: List<Meme>? = null

    @Before
    fun setUp() {
        subject = Mapper()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // TESTS                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    fun `map - happy path`() {
        givenDto("memes/memes_-_happy_path.json")
        whenMapped()
        thenDtoShouldBeMappedCorrectly(
                listOf(
                        Meme(
                                name = "One Does Not Simply",
                                imageUrl = "https://i.imgflip.com/1bij.jpg",
                                imageWidth = 568,
                                imageHeight = 335
                        ),
                        Meme(
                                name = "Batman Slapping Robin",
                                imageUrl = "https://i.imgflip.com/9ehk.jpg",
                                imageWidth = 400,
                                imageHeight = 387
                        ),
                        Meme(
                                name = "Expanding Brain",
                                imageUrl = "https://i.imgflip.com/1jwhww.jpg",
                                imageWidth = 857,
                                imageHeight = 1202
                        )
                )
        )
    }

    @Test
    fun `map - empty`() {
        givenDto("memes/memes_-_empty.json")
        whenMapped()
        thenDtoShouldBeMappedCorrectly(emptyList())
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // GIVEN                                                                                      //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun givenDto(filePath: String) {
        dto = jsonToObject(MemesDto::class.java, filePath, moshi)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // WHEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun whenMapped() {
        actual = subject.map(dto)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // THEN                                                                                       //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun thenDtoShouldBeMappedCorrectly(expected: List<Meme>) {
        assertThat(actual).isEqualTo(expected)
    }
}
