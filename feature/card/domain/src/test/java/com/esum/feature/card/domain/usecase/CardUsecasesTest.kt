package com.esum.feature.card.domain.usecase


import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.usecase.DeleteCardByIdUsecase
import com.esum.feature.card.domain.local.usecase.GetAllCardsUsecase
import com.esum.feature.card.domain.local.usecase.InsertCardUsecase
import com.esum.feature.card.domain.repository.FakeCardRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.UUID


class CardUsecasesTest {
    val TAG = "DeleteCardByIdUsecaseTest"

    val repository = FakeCardRepository()

    private lateinit var deleteByIdUsecase: DeleteCardByIdUsecase
    private lateinit var insertUsecase: InsertCardUsecase
    private lateinit var getAllCardsUsecase: GetAllCardsUsecase

    private val cards = mutableListOf<Card>()


    @Before
    fun setUp() {
        deleteByIdUsecase = DeleteCardByIdUsecase(repository)
        insertUsecase = InsertCardUsecase(repository)
        getAllCardsUsecase = GetAllCardsUsecase(repository)


        cards.add(
            Card(
                createDate = "",
                updateDate = "",
                originalLanguage = Languages.Farsi,
                original = "سلام",
            )
        )

    }

    @Test
    fun deleteById() = runBlocking {
        val id = UUID.randomUUID()
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