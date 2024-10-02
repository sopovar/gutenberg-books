package ge.sopovardidze.gutenberg_books.presentation.bookDetails

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ge.sopovardidze.gutenberg_books.databinding.FragmentBookDetailBinding
import ge.sopovardidze.gutenberg_books.presentation.base.BaseFragment
import ge.sopovardidze.gutenberg_books.presentation.utils.ViewBindingFactory
import ge.sopovardidze.gutenberg_books.presentation.utils.click

@AndroidEntryPoint
class BookDetailFragment : BaseFragment<FragmentBookDetailBinding>() {

    override val viewBindingFactory: ViewBindingFactory<FragmentBookDetailBinding> =
        FragmentBookDetailBinding::inflate

    override fun setUpUi() {
        val book = BookDetailFragmentArgs.fromBundle(requireArguments()).book
        Log.e("123123", "Book in details  - ${book.title}")
        with(binding) {
            val author = book.authors.first()
            tvBookDetailsAuthor.text = author?.name
            tvBookDetailsAuthorYears.text = "(${author?.birthYear}-${author?.deathYear})"
            tvBookDetailsTitle.text = book.title
            Glide
                .with(requireContext())
                .load(book.image)
                .centerCrop()
                .into(ivBookDetailsCover)
            tvBookDetailsDownloadCount.text = "${book.downloadedCount}"
            btnBookDetailsBack.click {
                findNavController().popBackStack()
            }
        }
    }
}
