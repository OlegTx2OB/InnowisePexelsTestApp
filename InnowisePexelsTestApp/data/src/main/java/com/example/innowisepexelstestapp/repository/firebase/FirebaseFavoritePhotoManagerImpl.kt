package com.example.innowisepexelstestapp.repository.firebase

import android.annotation.SuppressLint
import android.util.Log
import com.example.innowisepexelstestapp.dto.PhotoPexelsDto
import com.example.innowisepexelstestapp.mapper.PhotoPexelsMapper
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.repository.FavoritePhotoManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.concurrent.CountDownLatch

private const val FIREBASE_PHOTOS_DB = "FIREBASE_PHOTOS_DB"
private const val FIREBASE_INSTANCE_NAME = "https://pexels-app-9497d-default-rtdb.europe-west1.firebasedatabase.app"

class FirebaseFavoritePhotoManagerImpl : FavoritePhotoManager {

    private val mMapper = PhotoPexelsMapper()

    private val photosDbReference: DatabaseReference = FirebaseDatabase
        .getInstance(FIREBASE_INSTANCE_NAME).getReference(FIREBASE_PHOTOS_DB)

    @SuppressLint("CheckResult")
    override fun getAllFavoritePhoto(): List<PhotoPexels> {
        val photoDtoList = mutableListOf<PhotoPexelsDto>()
        val latch = CountDownLatch(1)

        photosDbReference.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (photoSnapshot in snapshot.children) {
                    val photo = photoSnapshot.getValue(PhotoPexelsDto::class.java)
                    photo?.let {
                        photoDtoList.add(it)
                    }
                }
                latch.countDown()
            }

            override fun onCancelled(error: DatabaseError) {
                error.toException().printStackTrace()
                latch.countDown()
            }

        })

        try {
            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return mMapper.toModels(photoDtoList)
    }

    override fun insertFavoritePhoto(photoPexels: PhotoPexels) {
        val photoPexelsDto = mMapper.toDto(photoPexels)
        photosDbReference.child(photoPexelsDto.id.toString()).setValue(photoPexelsDto)
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
    }

    override fun deleteFavoritePhoto(photoPexels: PhotoPexels) {
        val photoPexelsDto = mMapper.toDto(photoPexels)
        photosDbReference.child(photoPexelsDto.id.toString()).removeValue()
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
    }
}