package ge.sopovardidze.gutenberg_books.presentation.bookDetails

import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import ge.sopovardidze.gutenberg_books.databinding.FragmentBookDetailBinding
import ge.sopovardidze.gutenberg_books.presentation.base.BaseFragment
import ge.sopovardidze.gutenberg_books.presentation.utils.ViewBindingFactory

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>() {

    override val viewBindingFactory: ViewBindingFactory<FragmentBookDetailBinding> =
        FragmentBookDetailBinding::inflate

    override fun setUpUi() {
        val book = BookDetailFragmentArgs.fromBundle(requireArguments()).book
        Log.e("123123", "Book in details  - ${book.title}")
    }
}
