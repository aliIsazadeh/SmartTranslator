package com.esum.network.dataproviders

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import com.esum.common.lagnuage.Languages
import com.esum.network.TestRunner
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
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
    fun translate() = runBlocking {

        val response = provider.translate(
            fromLanguage = Languages.English,
            toLanguages = Languages.Farsi,
            query = "hello"
        )

        Log.d(TAG, response.responseData.translatedText)

        assertEquals("سلام", response.responseData.translatedText)

    }
}