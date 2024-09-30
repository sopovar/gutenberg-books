package ge.sopovardidze.gutenberg_books.presentation.bookList

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ge.sopovardidze.gutenberg_books.R
import ge.sopovardidze.gutenberg_books.databinding.FragmentBookListBinding
import ge.sopovardidze.gutenberg_books.presentation.BaseFragment
import ge.sopovardidze.gutenberg_books.presentation.ViewBindingFactory

@AndroidEntryPoint
class BookListFragment : BaseFragment<FragmentBookListBinding>() {

    override val viewBindingFactory: ViewBindingFactory<FragmentBookListBinding> =
        FragmentBookListBinding::inflate
    private val viewModel: BookListViewModel by viewModels()

    override fun setUpUi() {
        with(binding) {
            tvSampleFragment.text = "some random text in fragment"
            toDetails.setOnClickListener {
                findNavController().navigate(R.id.action_bookListFragment_to_bookDetailFragment)
            }
        }
    }
}
