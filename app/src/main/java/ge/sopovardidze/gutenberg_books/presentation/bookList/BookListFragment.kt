package ge.sopovardidze.gutenberg_books.presentation.bookList

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import ge.sopovardidze.gutenberg_books.R
import ge.sopovardidze.gutenberg_books.databinding.FragmentBookListBinding
import ge.sopovardidze.gutenberg_books.presentation.base.BaseFragment
import ge.sopovardidze.gutenberg_books.presentation.utils.ViewBindingFactory
import ge.sopovardidze.gutenberg_books.presentation.utils.gone
import ge.sopovardidze.gutenberg_books.presentation.utils.visible
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookListFragment : BaseFragment<FragmentBookListBinding>() {

    override val viewBindingFactory: ViewBindingFactory<FragmentBookListBinding> =
        FragmentBookListBinding::inflate
    private val viewModel: BookListViewModel by viewModels()

    private val adapter = BookListAdapter(
        onBookClick = {
            Log.e("123123", "click = ${it.title} - ${it.id}")
        }
    )

    override fun setUpUi() {
        with(binding) {
            rvBookList.adapter = adapter
        }
        getBooks()
        listenerAdapter()
    }

    private fun getBooks() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getPagingBooks.collectLatest { books ->
                    adapter.submitData(lifecycle, books)
                }
            }
        }
    }

    private fun listenerAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.addLoadStateListener { loadState ->
                    if (loadState.source.append is LoadState.Loading || loadState.source.refresh is LoadState.Loading) {
                        binding.progressBar.visible()
                    } else {
                        binding.progressBar.gone()
                    }
//                    val errorState = loadState.getError()
//                    errorState?.showError {
//                       // TODO()
//                    }
                }
            }
        }
    }
}
