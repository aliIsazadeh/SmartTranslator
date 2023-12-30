package com.esum.feature.card.domain.usecase


import com.esum.common.constraints.ResultConstraints
import com.esum.database.entity.CardEntity
import com.esum.feature.card.domain.repository.FakeCardRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class CardUsecasesTest {
    val TAG = "DeleteCardByIdUsecaseTest"

    val repository = FakeCardRepository()

    lateinit var deleteByIdUsecase: DeleteCardByIdUsecase
    lateinit var insertUsecase : InsertCardUsecase

    val cards = mutableListOf<CardEntity>()

    @Before
    fun setUp() {
        deleteByIdUsecase = DeleteCardByIdUsecase(repository)
        cards.add(CardEntity(id = 2 , active = true , farsi = "سگ" , english = "dog" ))
        cards.add(CardEntity(id = 2 , active = true , farsi = "گربه" , english = "cat" ))
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
    fun insertCard() = runBlocking {

    }

}