package com.esum.database.dataProvider

import android.util.Log
import com.esum.database.entity.CardEntity
import com.esum.database.entity.ProfileEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CardDataProviderTest {


    val TAG = "CardDataProviderTest"


    @get:Rule
    var hilt = HiltAndroidRule(this)

    @Inject
    lateinit var provider: CardDataProvider


    lateinit var cardEntity: CardEntity

    @Before
    fun setUp() {
        hilt.inject()
        cardEntity = CardEntity(active = true, correctAnswerCount = 0 , farsi = "سلام", english = "hello")
    }



    @Test
    fun insertProfile(): Unit = runBlocking {

        try {
            val id = provider.insertCard(cardEntity)
            assert(id > -1)
        } catch (e: Exception) {
            Assert.assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }
    }

    @Test
    fun updateProfile(): Unit = runBlocking {
        try {
            val id = provider.insertCard(cardEntity)

            var updateEntity: CardEntity? = null

            updateEntity = provider.getAllCards().first().first().copy(updateDate = "today")

            provider.updateCard(updateEntity)

            val updatedValue = provider.getCardById(updateEntity.id).first()

            Assert.assertEquals(updateEntity, updatedValue)

        } catch (e: Exception) {
            Assert.assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }
    }

    @Test
    fun deleteProfile(): Unit = runBlocking {
        try {

            val id = provider.insertCard(cardEntity)
            val deleteEntity = provider.getAllCards().first().first()
            provider.deleteCard(deleteEntity)
            val deleted: CardEntity? = provider.getCardById(deleteEntity.id).firstOrNull()

            Assert.assertEquals(null, deleted)


        } catch (e: Exception) {
            Assert.assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }


    }

}