package com.example.innowisepexelstestapp.presentation.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.innowisepexelstestapp.model.PhotoPexels
import com.example.innowisepexelstestapp.R
import com.example.innowisepexelstestapp.databinding.RvHomeItemBinding
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class RvPhotoAdapter(private val mListener: ClickListener) :
    RecyclerView.Adapter<RvPhotoAdapter.PhotoHolder>() {

    private val photoPexelsArray = ArrayList<PhotoPexels>()

    class PhotoHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RvHomeItemBinding.bind(view)

        fun bind(photoPexels: PhotoPexels, listener: ClickListener) = with(binding) {
            Picasso.get().load(photoPexels.url).into(rvHomeItem)
            itemView.setOnClickListener {
                listener.onClickPhoto(it as RoundedImageView, photoPexels)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_home_item, parent, false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photoPexelsArray[position], mListener)
    }

    override fun getItemCount(): Int {
        return photoPexelsArray.size
    }

    fun addPhotoPexelsList(photoPexelsList: List<PhotoPexels>) {
        //todo заебашить rxjava сюда
        photoPexelsArray.clear()
        photoPexelsArray.addAll(photoPexelsList)
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClickPhoto(view: RoundedImageView, photoPexels: PhotoPexels)
    }
}