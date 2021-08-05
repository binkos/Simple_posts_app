package com.vladislav.posts.testapplication.presentation.ui.allposts

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vladislav.posts.testapplication.R
import com.vladislav.posts.testapplication.domain.model.Post

@Composable
fun AllPosts(
    viewModel: AllPostsViewModel,
    navController: NavController
) {
    val isRefreshState = viewModel.isLoadingState.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshState.value),
        onRefresh = { viewModel.refreshPosts() }
    ) { Body(viewModel = viewModel, navController) }
}

@Composable
fun Body(viewModel: AllPostsViewModel, navController: NavController) {
    val allPosts = viewModel.allPostsState.collectAsState()
    val isDialogOpenState = viewModel.isDialogOpenState.collectAsState()

    ErrorState(
        isDialogOpenState,
        { viewModel.onDialogDismiss() },
        { viewModel.refreshPosts() }
    )

    SuccessState(
        posts = allPosts.value
    ) { itemId ->
        navController.navigate(AllPostsFragmentDirections.actionAllPostsToPostDetails(itemId))
    }
}

@Composable
fun SuccessState(posts: List<Post>, onItemClicked: (Long) -> Unit) {
    LazyColumn {
        items(posts) { message ->
            Text(
                text = message.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(2.dp, Color.Red, RoundedCornerShape(6.dp))
                    .clickable(
                        indication = rememberRipple(),
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { onItemClicked(message.id) }
                    )
                    .padding(8.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun ErrorState(
    isDialogOpenState: State<Boolean>,
    onDismiss: () -> Unit,
    onConfirmButtonClickListener: () -> Unit
) {

    if (isDialogOpenState.value)

        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(
                    text = stringResource(id = R.string.error_dialog_title),
                    color = Color.Black
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.error_dialog_message),
                    color = Color.Black
                )
            },
            dismissButton = {
                TextButton(
                    onClick = { onDismiss() }
                ) {
                    Text(
                        text = stringResource(id = R.string.dismiss_button_text),
                        color = Color.Black
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmButtonClickListener()
                        onDismiss()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.try_again_button_text),
                        color = Color.Black
                    )
                }
            },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        )
}