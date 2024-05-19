package com.dicoding.asclepius.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.core.data.repository.NewsRepository
import com.dicoding.asclepius.core.data.source.model.MedicalNewsModel

class MedicalViewModel : ViewModel() {

    private val newsRepository = NewsRepository()

    private val _medicalNews = MutableLiveData<List<MedicalNewsModel>>()
    val medicalNews: LiveData<List<MedicalNewsModel>>
        get() = _medicalNews

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun fetchMedicalNews() {
        newsRepository.fetchHealthNews(
            onSuccess = { newsList ->
                _medicalNews.postValue(newsList)
            },
            onError = { error ->
                _errorMessage.postValue(error)
            }
        )
    }
}
