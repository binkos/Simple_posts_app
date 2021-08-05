package com.vladislav.posts.testapplication.presentation.ui.postdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.vladislav.posts.testapplication.R
import com.vladislav.posts.testapplication.domain.model.PostDetails

@Composable
fun PostDetails(viewModel: PostDetailsViewModel) {
    val postDetailsState = viewModel.postDetailsStateFlow.collectAsState()
    val isRefreshState = viewModel.isLoadingState.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshState.value),
        onRefresh = { viewModel.refreshPostDetails() }
    ) {
        SuccessState(postDetailsState.value)
    }
}

@Composable
fun SuccessState(postDetails: PostDetails) {
    if (postDetails.userName == "" && postDetails.url == "") return

    val painter = rememberImagePainter(
        data = postDetails.url,
        builder = {
            crossfade(true)
            transformations(CircleCropTransformation())
        },
        imageLoader = ImageLoader
            .Builder(LocalContext.current)
            .networkCachePolicy(CachePolicy.ENABLED)
            .error(R.drawable.ic_error)
            .build()
    )

    if (painter.state is ImagePainter.State.Loading) {
        Box(Modifier.padding(start = 22.dp, top = 20.dp)) {
            CircularProgressIndicator()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row {
            Image(
                painter = painter,
                modifier = Modifier.size(64.dp),
                contentDescription = null
            )
            Text(
                text = postDetails.userName,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
        Text(
            text = postDetails.title,
            modifier = Modifier.padding(top = 8.dp),
            fontFamily = FontFamily.SansSerif,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = postDetails.body,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(top = 8.dp),
            fontFamily = FontFamily.SansSerif,
            fontSize = 14.sp
        )
    }
}