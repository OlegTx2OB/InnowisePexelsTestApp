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
            s.medium,
            s.small,
        )
        return PhotoPexelsDto(
            p.id,
            p.photographer,
            imageSourcesDto,
        )
    }

    fun toModel(p: PhotoPexelsDto) : PhotoPexels {
        val s = p.sources
        val imageSources = ImageSources(
            s.original,
            s.medium,
            s.small,

        )
        return PhotoPexels(
            p.id,
            p.photographer,
            imageSources,
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
