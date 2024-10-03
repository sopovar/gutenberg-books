package ge.sopovardidze.gutenberg_books.presentation.bookDetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.sopovardidze.gutenberg_books.databinding.ItemBookDetailSectionBinding

class BookDetailSectionItemAdapter(
    private val data: List<String>,
) : RecyclerView.Adapter<BookDetailSectionItemAdapter.BooDetailSectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooDetailSectionViewHolder {
        return BooDetailSectionViewHolder(
            ItemBookDetailSectionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BooDetailSectionViewHolder, position: Int) {
        val item = data[position]
        item.let {
            holder.bind(it)
        }
    }

    inner class BooDetailSectionViewHolder(private val binding: ItemBookDetailSectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String) {
            with(binding) {
                tvItemBookDetailSection.text = name
            }
        }
    }
}
