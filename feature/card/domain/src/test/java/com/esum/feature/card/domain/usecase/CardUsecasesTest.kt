package com.esum.feature.card.domain.usecase


import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.model.Card
import com.esum.feature.card.domain.repository.FakeCardRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class CardUsecasesTest {
    val TAG = "DeleteCardByIdUsecaseTest"

    val repository = FakeCardRepository()

    private lateinit var deleteByIdUsecase: DeleteCardByIdUsecase
    private lateinit var insertUsecase: InsertCardUsecase
    private lateinit var getAllCardsUsecase: GetAllCardsUsecase

    private val cardsEntity = mutableListOf<CardEntity>()
    private val cards = mutableListOf<Card>()


    @Before
    fun setUp() {
        deleteByIdUsecase = DeleteCardByIdUsecase(repository)
        insertUsecase = InsertCardUsecase(repository)
        getAllCardsUsecase = GetAllCardsUsecase(repository)

        cardsEntity.add(CardEntity(id = 2, active = true, farsi = "سگ", english = "dog"))
        cardsEntity.add(CardEntity(id = 2, active = true, farsi = "گربه", english = "cat"))

        cards.add(
            Card(
                createDate = "",
                updateDate = "",
                sentence = "",
                correctAnswerCount = 0,
                translateLanguage = Languages.English,
                translate = "hello",
                originalLanguage = Languages.Farsi,
                original = "سلام"
            )
        )

    }

    @Test
    fun deleteById() = runBlocking {
        val id = 1L
        deleteByIdUsecase.invoke(id).collect {
            when (it) {
                is ResultConstraints.Error -> {
                    assert(false) { it.message.toString() }
                }

                is ResultConstraints.Loading -> {
//                    Log.d(TAG, "invoke: Loading ")
                }

                is ResultConstraints.Success -> {
                    assert(true) { it.data.toString() }
                }
            }
        }
    }

    @Test
    fun getAllCards() = runBlocking {

        getAllCardsUsecase.invoke().collect { result ->
            when (result) {
                is ResultConstraints.Error -> {
                    assert(false) { result.message.toString() }
                }

                is ResultConstraints.Loading -> {}
                is ResultConstraints.Success -> {
                    assert(true) { result.data?.size.toString() }
                }
            }
        }
    }


    @Test
    fun insertCard() = runBlocking {

        insertUsecase.invoke(cards[0]).collect() { result ->

            when (result) {
                is ResultConstraints.Error -> {
                    assert(false)
                }

                is ResultConstraints.Loading -> {}
                is ResultConstraints.Success -> {
                    assert(true) { result.data.toString() }
                }
            }
        }
    }


}