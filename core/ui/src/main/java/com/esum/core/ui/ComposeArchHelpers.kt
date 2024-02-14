package com.esum.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

data class StateEffectDispatch<STATE, EFFECT, EVENT>(
    val state: STATE,
    val effectFlow: Flow<EFFECT>,
    val dispatch: (EVENT) -> Unit
)

interface UnidirectionalViewModel<STATE, EFFECT, EVENT> {
    val state: StateFlow<STATE>
    val effect: Flow<EFFECT>
    fun event(event: EVENT)
}



@Composable
inline fun <reified STATE, EFFECT, EVENT> use(
    viewModel: UnidirectionalViewModel<STATE, EFFECT, EVENT>
) : StateEffectDispatch<STATE,EFFECT,EVENT>{
    val state by viewModel.state.collectAsStateWithLifecycle()
    val dispatch: (EVENT) -> Unit = { event ->
        viewModel.event(event)
    }
    return StateEffectDispatch(
        state = state ,
        effectFlow =  viewModel.effect,
        dispatch = dispatch
    )
}

@Composable
fun <T> Flow<T>.CollectInLaunchedEffect(function : suspend (value : T) -> Unit) {
    val flow = this
    LaunchedEffect(key1 = flow){
        flow.collectLatest(function)
    }
}

