package com.dicoding.asclepius.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.core.data.source.model.MedicalNewsModel
import com.dicoding.asclepius.databinding.ItemNewsMedicalBinding

class MedicalNewsAdapter : ListAdapter<MedicalNewsModel, MedicalNewsAdapter.NewsViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsMedicalBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    inner class NewsViewHolder(private val binding: ItemNewsMedicalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: MedicalNewsModel) {
            binding.apply {
                tvTitleNews.text = news.title
                tvMedia.text = news.author

                Glide.with(itemView)
                    .load(news.imageUrl)
                    .into(imageView)

                binding.btnRead.setOnClickListener{
                    openUrl(root.context, news.url)
                }
            }
        }
    }
    private fun openUrl(context:Context, url: String?) {
        url?.let { urlString ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(urlString)
            context.startActivity(intent)
        }
    }

    private class NewsDiffCallback : DiffUtil.ItemCallback<MedicalNewsModel>() {
        override fun areItemsTheSame(oldItem: MedicalNewsModel, newItem: MedicalNewsModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MedicalNewsModel, newItem: MedicalNewsModel): Boolean {
            return oldItem == newItem
        }
    }
}
