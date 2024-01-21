package com.esum.database.dataProvider

import android.util.Log
import com.esum.database.entity.CardEntity
import com.esum.database.entity.Language
import com.esum.database.entity.ProfileEntity
import com.esum.database.entity.relations.CardWithLanguages
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.UUID
import javax.inject.Inject

@HiltAndroidTest
class CardDataProviderTest {


    val TAG = "CardDataProviderTest"


    @get:Rule
    var hilt = HiltAndroidRule(this)

    @Inject
    lateinit var provider: CardDataProvider


    lateinit var cardEntity: CardWithLanguages

    @Before
    fun setUp() {
        hilt.inject()
        cardEntity = CardWithLanguages(
            cardEntity = CardEntity(
                image = null,
                createDate ="",
                updateDate ="",
                active =true,
                id = UUID.randomUUID(),
                defineLanguage = "",
                defineText =""
            ), language = listOf()
        )
    }


    @Test
    fun insertProfile(): Unit = runBlocking {

        try {
            val id = provider.insertCard(cardEntity.cardEntity)
            assert(id.toString().isNotBlank())
        } catch (e: Exception) {
            Assert.assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }
    }

    @Test
    fun updateProfile(): Unit = runBlocking {
        try {
            val id = provider.insertCard(cardEntity.cardEntity)

            var updateEntity: CardWithLanguages? = null

            updateEntity = provider.getAllCards().first().first().copy(cardEntity = cardEntity.cardEntity.copy(updateDate = "today"))

            provider.updateCard(updateEntity.cardEntity)

            val updatedValue = provider.getCardById(updateEntity.cardEntity.id).first()

            Assert.assertEquals(updateEntity, updatedValue)

        } catch (e: Exception) {
            Assert.assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }
    }

    @Test
    fun deleteProfile(): Unit = runBlocking {
        try {

            val id = provider.insertCard(cardEntity.cardEntity)
            val deleteEntity = provider.getAllCards().first().first()
            provider.deleteCard(deleteEntity.cardEntity)
            val deleted: CardWithLanguages? = provider.getCardById(deleteEntity.cardEntity.id).firstOrNull()

            Assert.assertEquals(null, deleted)


        } catch (e: Exception) {
            Assert.assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }


    }

}