package com.dicoding.asclepius.core.data.repository

import com.dicoding.asclepius.core.data.source.model.MedicalNewsModel
import com.dicoding.asclepius.core.data.source.remote.network.ApiConfig
import com.dicoding.asclepius.core.data.source.remote.response.MedicalNews
import com.dicoding.asclepius.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    fun fetchHealthNews(
        onSuccess: (List<MedicalNewsModel>) -> Unit,
        onError: (String) -> Unit
    ) {
        val callback = object : Callback<MedicalNews> {
            override fun onResponse(call: Call<MedicalNews>, response: Response<MedicalNews>) {
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    val newsData = articles.mapNotNull { article ->
                        if (!article?.title.isNullOrEmpty() && !article?.urlToImage.isNullOrEmpty() && !article?.author.isNullOrEmpty() && !article?.url.isNullOrEmpty()) {
                            MedicalNewsModel(article!!.title!!, article.urlToImage!!, article.author!!, article.url!!)
                        } else {
                            null
                        }
                    }
                    onSuccess(newsData)
                } else {
                    onError("Gagal mengambil berita")
                }
            }

            override fun onFailure(call: Call<MedicalNews>, t: Throwable) {
                onError(t.message ?: "Terjadi kesalahan")
            }
        }

        ApiConfig.newsApiService.medicalNews("cancer", "health", "en", Constants.API_KEY)
            .enqueue(callback)
    }


}
