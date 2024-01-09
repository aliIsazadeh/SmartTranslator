package com.esum.network.translate.dataproviders

import android.util.Log
import com.esum.common.lagnuage.Languages
import com.esum.network.translate.dataprovider.TranslateDataProvider
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class TranslateDataProviderTest {

    val TAG = "TranslateDataProviderTest"

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var provider: TranslateDataProvider

    @Before
    fun setUp() {
        hiltRule.inject()

    }

    @Test
    fun translate(): Unit = runBlocking {

        try{
            val response = provider.translate(
                fromLanguage = Languages.English.key,
                toLanguages = Languages.Farsi.key,
                query = "Hello"
            )
            Log.d(TAG, response.toString())

            assert(response.isSuccessful)
        }catch (e : Exception){
            Log.e(TAG, "translate: ${e.message}", )
            assert(false)
        }

  

    }
}