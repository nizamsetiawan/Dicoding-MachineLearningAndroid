package com.dicoding.asclepius.ui.adapter
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.core.data.local.PredictionHistory
import com.dicoding.asclepius.databinding.ItemNewsHistoryBinding
import java.io.File

class HistoryAdapter(private val onDeleteClickListener: (PredictionHistory) -> Unit) : ListAdapter<PredictionHistory, HistoryAdapter.PredictionViewHolder>(PredictionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredictionViewHolder {
        val binding = ItemNewsHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PredictionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PredictionViewHolder, position: Int) {
        val prediction = getItem(position)
        holder.bind(prediction)
    }

    inner class PredictionViewHolder(private val binding: ItemNewsHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(prediction: PredictionHistory) {
            with(binding) {
                tvTitleHistory.text = "Analysis Results"
                tvResult.text = prediction.result


                val imageUri = Uri.parse(prediction.imagePath)
                val imageFile = File(imageUri.path)
                if (imageFile.exists()) {
                    imageView.setImageURI(imageUri)
                }

                btnDelete.setOnClickListener {
                    onDeleteClickListener(prediction)
                }
            }
        }
    }

    private class PredictionDiffCallback : DiffUtil.ItemCallback<PredictionHistory>() {
        override fun areItemsTheSame(oldItem: PredictionHistory, newItem: PredictionHistory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PredictionHistory, newItem: PredictionHistory): Boolean {
            return oldItem == newItem
        }
    }
}


