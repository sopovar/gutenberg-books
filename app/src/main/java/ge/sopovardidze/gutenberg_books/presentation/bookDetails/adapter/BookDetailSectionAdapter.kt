package ge.sopovardidze.gutenberg_books.presentation.bookDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ge.sopovardidze.gutenberg_books.databinding.ItemBookDetailBinding
import ge.sopovardidze.gutenberg_books.presentation.bookDetails.SectionModel

class BookDetailSectionAdapter(
    private val data: List<SectionModel>,
) : RecyclerView.Adapter<BookDetailSectionAdapter.BookDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookDetailViewHolder {
        return BookDetailViewHolder(
            ItemBookDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: BookDetailViewHolder, position: Int) {
        val item = data[position]
        item.let {
            holder.bind(it)
        }
    }

    inner class BookDetailViewHolder(private val binding: ItemBookDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(section: SectionModel) {
            with(binding) {
                tvBookDetailTitle.text = section.title
                val adapter = BookDetailSectionItemAdapter(section.items)
                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                rvBookDetailSection.layoutManager = staggeredGridLayoutManager
                rvBookDetailSection.adapter = adapter
            }
        }
    }
}
