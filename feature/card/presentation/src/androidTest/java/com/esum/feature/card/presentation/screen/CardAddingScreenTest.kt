package com.esum.feature.card.presentation.screen

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esum.common.constraints.ResultConstraints
import com.esum.common.lagnuage.Languages
import com.esum.feature.card.domain.local.model.Card
import com.esum.feature.card.domain.local.model.TranslateResult
import com.esum.feature.card.domain.local.usecase.InsertCardUsecase
import com.esum.feature.card.domain.remote.description.usecase.GetDescriptionUsecase
import com.esum.feature.card.domain.remote.translate.usecase.TranslateCardUseCase
import com.esum.feature.card.presentation.viewmodel.AddingCardViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Provider


@HiltAndroidTest
class CardAddingScreenTest {

    val ADD_CARD_SCREEN_ROUTE = "add_card_screen"

    val card: Card = Card(
        originalLanguage = Languages.English,
        translate = "سلام",
        translateLanguage = Languages.Farsi,
        sentence = null,
        updateDate = "",
        createDate = "",
        original = "hello",
        correctAnswerCount = 0
    )

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @MockK
    private lateinit var insertCardUsecase: InsertCardUsecase

    @MockK
    lateinit var translateCardUseCase: TranslateCardUseCase

    @MockK
    lateinit var getDescriptionUsecase: GetDescriptionUsecase

    private lateinit var viewModel: AddingCardViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Before
    fun setUp() {
        hiltRule.inject()
        MockKAnnotations.init(this)

        coEvery { insertCardUsecase.invoke(card) } coAnswers {
            flowOf(ResultConstraints.Success(1L))
        }
        coEvery {
            translateCardUseCase.invoke(
                fromLanguages = card.originalLanguage,
                toLanguages = card.translateLanguage,
                text = card.original
            )
        } coAnswers {
            flowOf(ResultConstraints.Success(data = TranslateResult(translated = card.translate)))
        }

        viewModel = AddingCardViewModel(insertCardUsecase = { insertCardUsecase },
            translateCardUseCase = { translateCardUseCase } , {getDescriptionUsecase})

        composeTestRule.setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = ADD_CARD_SCREEN_ROUTE) {
                composable(route = ADD_CARD_SCREEN_ROUTE) {
                    CardAddingScreen(
                        windowSize = WindowSizeClass.Companion.calculateFromSize(
                            size = DpSize(
                                400.dp,
                                600.dp
                            )
                        ),
                        navController = navController,
                        viewModel = viewModel,
                    )
                }
            }
        }
    }


    @Test
    fun translateTest() {

        composeTestRule.onNodeWithTag("original_text_field").performTextInput(card.original)

        val picker = composeTestRule.onAllNodes(hasTestTag("lazy_column")).onFirst()
        picker.performScrollToIndex(0)

        composeTestRule.onNodeWithTag("online_translate_tag").performClick()

        composeTestRule.onNodeWithTag("translate_text_field").assertTextEquals(card.translate)

    }

    @Test
    fun addCardTest(){

        composeTestRule.onNodeWithTag("original_text_field").performTextInput(card.original)


        val picker = composeTestRule.onAllNodes(hasTestTag("lazy_column")).onFirst()
        picker.performScrollToIndex(0)

        composeTestRule.onNodeWithTag("online_translate_tag").performClick()

        composeTestRule.onNodeWithTag("save_card_btn").performClick()

        composeTestRule.onNodeWithTag("snack_bar").assertExists()



    }


}