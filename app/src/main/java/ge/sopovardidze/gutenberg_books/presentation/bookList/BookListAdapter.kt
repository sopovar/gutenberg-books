package ge.sopovardidze.gutenberg_books.presentation.bookList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ge.sopovardidze.gutenberg_books.data.local.BookEntity
import ge.sopovardidze.gutenberg_books.databinding.ItemBookBinding
import ge.sopovardidze.gutenberg_books.presentation.utils.click

class BookListAdapter(private val onBookClick: (BookEntity) -> Unit) :
    PagingDataAdapter<BookEntity, BookListAdapter.BookListViewHolder>(BookComparator) {

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        return BookListViewHolder(
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class BookListViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: BookEntity) {
            with(binding) {
                tvTitle.text = book.title
                root.click {
                    onBookClick.invoke(book)
                }
            }
        }
    }

    object BookComparator : DiffUtil.ItemCallback<BookEntity>() {
        override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
            return oldItem == newItem
        }
    }
}
