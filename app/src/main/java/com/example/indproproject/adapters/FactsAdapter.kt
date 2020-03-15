package com.example.indproproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.indproproject.R
import com.example.indproproject.databinding.ItemFactsLayoutBinding
import com.example.indproproject.listener.ItemClickListener
import com.example.indproproject.models.Row

/**
 * this adapter is used to set lists of facts
 */
class FactsAdapter(var itemClickListener: ItemClickListener) : RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {

    private var rows: List<Row> = ArrayList<Row>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val binding: ItemFactsLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_facts_layout,
            parent,
            false
        )
        return FactsViewHolder(binding)
    }

    fun addRowList(rows: List<Row>) {
        this.rows = rows
    }

    inner class FactsViewHolder(binding: ItemFactsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val itemLayoutBinding: ItemFactsLayoutBinding?
        init {
            itemLayoutBinding = binding
        }
        fun bindFacts(position: Int, itemClickListener: ItemClickListener) {
            itemLayoutBinding!!.model = rows[position]
            itemLayoutBinding.pos = position
            itemLayoutBinding.listener = itemClickListener
        }
    }

    override fun getItemCount() = rows.size

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.bindFacts(position, itemClickListener)
    }
}

@BindingAdapter("android:src")
fun setImageViewResource(
    imageView: ImageView,
    imageUri: String?
) {
    if (imageUri != null) {
        Glide.with(imageView.context).load(imageUri)
            .error(R.drawable.ic_launcher_background)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
}
