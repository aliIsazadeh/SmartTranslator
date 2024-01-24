package com.esum.database.dataProvider

import android.util.Log
import com.esum.database.dataProvider.card.CardDataProvider
import com.esum.database.dataProvider.card.CardGetReviewsDataProvider
import com.esum.database.dataProvider.card.CardInsertDataProvider
import com.esum.database.dataProvider.languag.LanguageInsertDataProvider
import com.esum.database.dataProvider.languag.LanguageProvider
import com.esum.database.entity.CardEntity
import com.esum.database.entity.Language
import com.esum.database.entity.relations.CardWithLanguages
import com.esum.database.entity.relations.LanguageWithDescriptions
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
    @Inject
    lateinit var languageProvider : LanguageProvider
    @Inject
    lateinit var cardInsertProvider : CardInsertDataProvider
    @Inject
    lateinit var languageInsertDataProvider: LanguageInsertDataProvider

    @Inject
    lateinit var cardReview : CardGetReviewsDataProvider


    lateinit var cardEntity: CardWithLanguages
    lateinit var cardEntityDisActive: CardWithLanguages


    @Before
    fun setUp() {
        val cardId = UUID.randomUUID()
        val deActiveCardId = UUID.randomUUID()

        hilt.inject()
        cardEntity = CardWithLanguages(
            cardEntity = CardEntity(
                image = null,
                createDate = "20240120",
                updateDate = "20240120",
                active = true,
                id = cardId,
                defineLanguage = "fa",
                defineText = ""
            ),
            language = null
        )
        cardEntityDisActive = CardWithLanguages(
            cardEntity = CardEntity(
                image = null,
                createDate = "20240120",
                updateDate = "20240120",
                active = false,
                id = deActiveCardId,
                defineLanguage = "",
                defineText = "fa"
            ), language = null
        )
    }


    @Test
    fun insertProfile(): Unit = runBlocking {

        try {
            val id = cardInsertProvider.insertCard(cardEntity.cardEntity)
            assert(id.toString().isNotBlank())
        } catch (e: Exception) {
            Assert.assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }
    }

    @Test
    fun updateProfile(): Unit = runBlocking {
        try {
            val id = cardInsertProvider.insertCard(cardEntity.cardEntity)

            var updateEntity: CardWithLanguages? = null

            updateEntity = provider.getAllCards().first().first()
                .copy(cardEntity = cardEntity.cardEntity.copy(updateDate = "today"))

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

            val id = cardInsertProvider.insertCard(cardEntity.cardEntity)
            val deleteEntity = provider.getAllCards().first().first()
            provider.deleteCard(deleteEntity.cardEntity)
            val deleted: CardWithLanguages? =
                provider.getCardById(deleteEntity.cardEntity.id).firstOrNull()

            Assert.assertEquals(null, deleted)


        } catch (e: Exception) {
            Assert.assertEquals(e.message.toString(), 1, 0)
            Log.e(TAG, "insertProfile: ${e.message}")
        }


    }

    @Test
    fun getActiveCounts(): Unit = runBlocking {
        try {
            cardInsertProvider.insertCard(cardEntity.cardEntity)
            cardInsertProvider.insertCard(cardEntityDisActive.cardEntity)

            val cardsCount = provider.getActiveCardsCount().first()

            Assert.assertEquals("actives", cardsCount.firstOrNull { it.active }?.count, 1)
            Assert.assertEquals("deActives", cardsCount.firstOrNull { !it.active }?.count, 1)


        } catch (e: Exception) {
            Assert.assertFalse(e.message, false)
            Log.e(TAG, "getActiveCounts: ${e.message} ")
        }
    }

    @Test
    fun reviewCardsTest(): Unit = runBlocking {
        try {
            cardInsertProvider.insertCard(cardEntity.cardEntity)
            cardInsertProvider.insertCard(cardEntityDisActive.cardEntity)

            cardEntity.language.let{
                languageInsertDataProvider.insertLanguage(it!!.language)
            }
            cardEntityDisActive.language.let{
                languageInsertDataProvider.insertLanguage(it!!.language)
            }

            val getCards = provider.getAllCards().first()
            Log.d(TAG, "getCards: ${getCards.size}")

            val reviewCards = cardReview.getReviewCards().first()


            Log.d(TAG, "reviewCardsTest: ${reviewCards.size}")
            assert(reviewCards.size == 1)


        } catch (e: Exception) {
            assert(false)

        }
    }


}