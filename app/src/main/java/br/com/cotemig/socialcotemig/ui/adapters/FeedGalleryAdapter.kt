package br.com.cotemig.socialcotemig.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.PostImage
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_gallery.view.*

class FeedGalleryAdapter(
    var context: Context,
    var list: List<PostImage>,
    var listener: FeedGalleryAdapterListener
) :
    RecyclerView.Adapter<FeedGalleryAdapter.FeedGalleryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedGalleryHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false)
        return FeedGalleryHolder(view)
    }

    override fun onBindViewHolder(holder: FeedGalleryHolder, position: Int) {
        holder.bind(context, list[position], listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FeedGalleryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, postImage: PostImage, listener: FeedGalleryAdapterListener) {
            Glide.with(context).load(postImage.image).into(itemView.image)

            itemView.image.setOnClickListener {
                listener.likePhoto(postImage)
            }

        }

    }

    interface FeedGalleryAdapterListener {

        fun likePhoto(postImage: PostImage)

    }

}