package com.example.innowisepexelstestapp.mapper

import com.example.innowisepexelstestapp.dto.ImageSourcesDto
import com.example.innowisepexelstestapp.dto.PhotoPexelsDto
import com.example.innowisepexelstestapp.model.ImageSources
import com.example.innowisepexelstestapp.model.PhotoPexels

class PhotoPexelsMapper {

    fun toDto(p: PhotoPexels) : PhotoPexelsDto {
        val s = p.sources
        val imageSourcesDto = ImageSourcesDto(
            s.original,
            s.extraLarge,
            s.large,
            s.medium,
            s.small,
            s.portrait,
            s.landscape,
            s.tiny
        )
        return PhotoPexelsDto(
            p.id,
            p.width,
            p.height,
            p.url,
            p.photographer,
            p.photographerURL,
            p.photographerID,
            p.averageColor,
            imageSourcesDto,
            p.liked,
            p.description
        )
    }

    fun toModel(p: PhotoPexelsDto) : PhotoPexels {
        val s = p.sources
        val imageSources = ImageSources(
            s.original,
            s.extraLarge,
            s.large,
            s.medium,
            s.small,
            s.portrait,
            s.landscape,
            s.tiny
        )
        return PhotoPexels(
            p.id,
            p.width,
            p.height,
            p.url,
            p.photographer,
            p.photographerUrl,
            p.photographerId,
            p.averageColor,
            imageSources,
            p.liked,
            p.description
        )
    }

    fun toDtos(pList: List<PhotoPexels>): List<PhotoPexelsDto> {
        val dtoList = mutableListOf<PhotoPexelsDto>()
        for (p in pList) {
            dtoList.add(toDto(p))
        }
        return dtoList
    }

    fun toModels(dtoList: List<PhotoPexelsDto>): List<PhotoPexels> {
        val pList = mutableListOf<PhotoPexels>()
        for (p in dtoList) {
            pList.add(toModel(p))
        }
        return pList
    }
}
