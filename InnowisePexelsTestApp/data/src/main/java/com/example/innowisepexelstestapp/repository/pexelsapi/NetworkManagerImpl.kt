package com.example.innowisepexelstestapp.repository.pexelsapi

import com.example.innowisepexelstestapp.dto.CuratedResultDto
import com.example.innowisepexelstestapp.mapper.PhotoPexelsMapper
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.repository.NetworkManager
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NetworkManagerImpl(private val mNetworkClient: PexelsNetworkClient) : NetworkManager {

    private val mMapper = PhotoPexelsMapper()
    override fun getCuratedPhotos(): Single<List<PhotoPexels>> {
        return Single.fromCallable {
            val response = mNetworkClient.getResponseWithCuratedPhotos()
            if (!response.isSuccessful) {
                //todo мб иначе обработать
                throw Exception("Failed to get curated photos")
            }

            val curatedResultDto: CuratedResultDto = Gson().fromJson(response.body?.string(), CuratedResultDto::class.java)
            return@fromCallable mMapper.toModels(curatedResultDto.photos)
        }.subscribeOn(Schedulers.io())
    }


}