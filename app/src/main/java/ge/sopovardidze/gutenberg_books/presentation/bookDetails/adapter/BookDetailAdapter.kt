package ge.sopovardidze.gutenberg_books.presentation.bookDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ge.sopovardidze.gutenberg_books.databinding.LayoutBookDetailItemBinding
import ge.sopovardidze.gutenberg_books.domain.model.Book
import ge.sopovardidze.gutenberg_books.presentation.bookDetails.SectionModel
import ge.sopovardidze.gutenberg_books.presentation.utils.click

class BookDetailAdapter(
    private val book: Book,
    private val onBackClick: () -> Unit
): RecyclerView.Adapter<BookDetailAdapter.SampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(
            LayoutBookDetailItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(onBackClick)
    }


    inner class SampleViewHolder(private val binding: LayoutBookDetailItemBinding): RecyclerView.ViewHolder(binding.root) {

        private lateinit var bookDetailAdapter: BookDetailSectionAdapter

        fun bind(onBackClick: () -> Unit) {
            with(binding) {
                val author = book.authors.first()
                tvBookDetailsAuthor.text = author?.name
                tvBookDetailsAuthorYears.text =
                    "(${author?.birthYear}-${author?.deathYear})"
                tvBookDetailsTitle.text = book.title
                Glide
                    .with(root.context)
                    .load(book.image)
                    .centerCrop()
                    .into(ivBookDetailsCover)
                tvBookDetailsDownloadCount.text = "${book.downloadedCount}"
                val data = listOf(
                    SectionModel("Subjects", book.subjects),
                    SectionModel("Bookshelves", book.bookshelves),
                )

                bookDetailAdapter = BookDetailSectionAdapter(data)
                rvBookDetail.adapter = bookDetailAdapter
                rvBookDetail.isNestedScrollingEnabled = true
                btnBookDetailsBack.click { onBackClick.invoke() }
            }
        }
    }
}