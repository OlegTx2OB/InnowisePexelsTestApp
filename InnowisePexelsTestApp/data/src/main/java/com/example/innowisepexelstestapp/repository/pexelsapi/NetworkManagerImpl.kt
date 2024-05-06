package com.example.innowisepexelstestapp.repository.pexelsapi

import com.example.innowisepexelstestapp.dto.CollectionsResultDto
import com.example.innowisepexelstestapp.dto.CuratedResultDto
import com.example.innowisepexelstestapp.mapper.CategoryMapper
import com.example.innowisepexelstestapp.mapper.PhotoPexelsMapper
import com.example.innowisepexelstestapp.model.Category
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.repository.NetworkManager
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NetworkManagerImpl(private val mNetworkClient: PexelsNetworkClient) : NetworkManager {

    private val mPhotoMapper = PhotoPexelsMapper()
    private val mCategoryMapper = CategoryMapper()
    override fun getCuratedPhotos(): Single<List<PhotoPexels>> {
        return Single.fromCallable {
            val response = mNetworkClient.getResponseWithCuratedPhotos()
            if (!response.isSuccessful) {
                //todo мб иначе обработать
                throw Exception("Failed to get curated photos")
            }

            val curatedResultDto: CuratedResultDto = Gson().fromJson(response.body?.string(), CuratedResultDto::class.java)
            return@fromCallable mPhotoMapper.toModels(curatedResultDto.photos)
        }.subscribeOn(Schedulers.computation())
    }

    override fun getCategories(): Single<List<Category>> {
        return Single.fromCallable {
            val response = mNetworkClient.getResponseWithFeaturedCategories()
            if (!response.isSuccessful) {
                //todo мб иначе обработать
                throw Exception("Failed to get curated photos")
            }
            val collectionsResultDto: CollectionsResultDto = Gson().fromJson(response.body?.string(), CollectionsResultDto::class.java)
            return@fromCallable mCategoryMapper.toModels(collectionsResultDto.collections)
        }.subscribeOn(Schedulers.computation())
    }
}