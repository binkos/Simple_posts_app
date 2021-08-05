package com.vladislav.posts.testapplication.presentation.ui.postdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.vladislav.posts.testapplication.app.App
import com.vladislav.posts.testapplication.ui.theme.PostsApplicationTheme
import com.vladislav.posts.testapplication.utils.viewmodel.fragmentViewModelProvider

class PostDetailsFragment : Fragment() {

    private val viewModel by fragmentViewModelProvider {
        (requireActivity().applicationContext as App).appComponent
            .run {
                val itemId = PostDetailsFragmentArgs.fromBundle(requireArguments()).postId
                PostDetailsViewModel(postsUseCase, itemId)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            PostsApplicationTheme {
                Surface {
                    PostDetails(viewModel)
                }
            }
        }
    }
}