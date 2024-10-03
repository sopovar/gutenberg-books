package ge.sopovardidze.gutenberg_books.presentation.bookDetails

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ge.sopovardidze.gutenberg_books.databinding.FragmentBookDetailBinding
import ge.sopovardidze.gutenberg_books.presentation.base.BaseFragment
import ge.sopovardidze.gutenberg_books.presentation.utils.ViewBindingFactory
import ge.sopovardidze.gutenberg_books.presentation.utils.click
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>() {

    override val viewBindingFactory: ViewBindingFactory<FragmentBookDetailBinding> =
        FragmentBookDetailBinding::inflate
    private val viewModel: BookDetailsViewModel by viewModels()

    override fun setUpUi() {
        val bookId = arguments?.getInt("bookId")
        Log.e("123123", "bookId = ${bookId}")
        if (bookId != null) {
            viewModel.getBookById(bookId)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bookDetails.collectLatest { book ->
                    with(binding) {
                        book?.let {
                            val author = book.authors.first()
                            tvBookDetailsAuthor.text = author?.name
                            tvBookDetailsAuthorYears.text =
                                "(${author?.birthYear}-${author?.deathYear})"
                            tvBookDetailsTitle.text = book.title
                            Glide
                                .with(requireContext())
                                .load(book.image)
                                .centerCrop()
                                .into(ivBookDetailsCover)
                            tvBookDetailsDownloadCount.text = "${book.downloadedCount}"
                        }
                        btnBookDetailsBack.click {
                            findNavController().popBackStack()
                        }
                    }
                }
            }
        }
//        val book = BookDetailFragmentArgs.fromBundle(requireArguments()).book
    }
}
