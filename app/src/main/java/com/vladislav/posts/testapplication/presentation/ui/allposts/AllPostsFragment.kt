package com.vladislav.posts.testapplication.presentation.ui.allposts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vladislav.posts.testapplication.app.App
import com.vladislav.posts.testapplication.ui.theme.PostsApplicationTheme
import com.vladislav.posts.testapplication.utils.viewmodel.fragmentViewModelProvider

class AllPostsFragment : Fragment() {

    private val viewModel by fragmentViewModelProvider {
        (requireActivity().applicationContext as App).appComponent
            .run { AllPostsViewModel(postsUseCase) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext())
        .apply {
            setContent {
                PostsApplicationTheme {
                    Surface {
                        AllPosts(viewModel = viewModel, findNavController())
                    }
                }
            }
        }
}