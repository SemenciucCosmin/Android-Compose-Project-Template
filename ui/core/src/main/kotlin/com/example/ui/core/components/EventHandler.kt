package com.example.ui.core.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.core.viewmodel.model.Event
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow

/**
 * An effect for handling events from view model.
 *
 * @param eventsFlow State flow with list of [Event] subtype that acts as a queue
 * @param minActiveState [Lifecycle.State] in which the upstream flow gets collected. The collection
 *   will stop if the lifecycle falls below that state, and will restart if it's in that state
 *   again.
 * @param onEvent the action invoked by having at least one event object in the list
 */
@Composable
fun <T : Event> EventHandler(
    eventsFlow: StateFlow<ImmutableList<T>>,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    onEvent: @Composable (T) -> Unit,
) {
    val events by eventsFlow.collectAsStateWithLifecycle(minActiveState = minActiveState)
    val event by remember { derivedStateOf { events.firstOrNull() } }
    event?.let { onEvent(it) }
}
