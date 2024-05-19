package com.dicoding.asclepius.ui.history
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.core.data.local.PredictionDatabase
import com.dicoding.asclepius.core.data.local.PredictionHistory
import com.dicoding.asclepius.databinding.FragmentHistoryBinding
import com.dicoding.asclepius.ui.adapter.HistoryAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyAdapter = HistoryAdapter { prediction ->
            deletePrediction(prediction)
        }

        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }

        loadPredictionsFromDatabase()
    }

    private fun loadPredictionsFromDatabase() {
        GlobalScope.launch(Dispatchers.IO) {
            val predictions = PredictionDatabase.getDatabase(requireContext()).predictionHistoryDao().getAllPredictions()
            requireActivity().runOnUiThread {
                historyAdapter.submitList(predictions)
            }
        }
    }

    private fun deletePrediction(prediction: PredictionHistory) {
        GlobalScope.launch(Dispatchers.IO) {
            PredictionDatabase.getDatabase(requireContext()).predictionHistoryDao().deletePrediction(prediction.id)
            loadPredictionsFromDatabase()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

