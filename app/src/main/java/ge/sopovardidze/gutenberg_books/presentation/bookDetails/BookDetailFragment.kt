package ge.sopovardidze.gutenberg_books.presentation.bookDetails

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ge.sopovardidze.gutenberg_books.databinding.FragmentBookDetailBinding
import ge.sopovardidze.gutenberg_books.domain.model.Book
import ge.sopovardidze.gutenberg_books.presentation.base.BaseFragment
import ge.sopovardidze.gutenberg_books.presentation.bookDetails.adapter.BookDetailAdapter
import ge.sopovardidze.gutenberg_books.presentation.utils.ARG_BOOK_ID
import ge.sopovardidze.gutenberg_books.presentation.utils.ViewBindingFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>() {

    override val viewBindingFactory: ViewBindingFactory<FragmentBookDetailBinding> =
        FragmentBookDetailBinding::inflate
    private val viewModel: BookDetailsViewModel by viewModels()

    override fun setUpUi() {
        val bookId = arguments?.getInt(ARG_BOOK_ID)
        if (bookId != null) {
            viewModel.getBookById(bookId)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { result ->
                    result.book?.let { book ->
                        setupBookDetails(book)
                    } ?: result.errorMsg?.let { errorMsg ->
                        showError(errorMsg)
                    }
                }
            }
        }
    }

    private fun setupBookDetails(book: Book) {
        with(binding) {
            rvBookDetail.apply {
                adapter = BookDetailAdapter(book) {
                    findNavController().popBackStack()
                }
                isNestedScrollingEnabled = false
            }
        }
    }

    private fun showError(errorMsg: String) {
        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_LONG).show()
    }
}
