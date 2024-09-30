package ge.sopovardidze.gutenberg_books.presentation.bookDetails

import dagger.hilt.android.AndroidEntryPoint
import ge.sopovardidze.gutenberg_books.databinding.FragmentBookDetailBinding
import ge.sopovardidze.gutenberg_books.presentation.BaseFragment
import ge.sopovardidze.gutenberg_books.presentation.ViewBindingFactory

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>() {

    override val viewBindingFactory: ViewBindingFactory<FragmentBookDetailBinding> = FragmentBookDetailBinding::inflate

    override fun setUpUi() {
    }

}