package br.com.cotemig.socialcotemig.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.Post
import br.com.cotemig.socialcotemig.models.PostImage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_sign_up.view.*
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedAdapter(var context: Context, var list: List<Post>, var listener: FeedAdapterListener) :
    RecyclerView.Adapter<FeedAdapter.FeedHolder>(), FeedGalleryAdapter.FeedGalleryAdapterListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false)
        return FeedHolder(view)
    }

    override fun onBindViewHolder(holder: FeedHolder, position: Int) {
        holder.bind(this, context, list[position], listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun likePhoto(postImage: PostImage) {
        listener.likePhoto(postImage)
    }

    class FeedHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            adapter: FeedAdapter,
            context: Context,
            post: Post,
            listener: FeedAdapterListener
        ) {

            itemView.description.text = post.description
            itemView.user.text = post.user
            itemView.date.text = post.date
            itemView.likes.text = post.likes.size.toString()
            itemView.local.text = post.local

            Glide.with(context).load(post.avatar)
                .apply(RequestOptions().
                transform(RoundedCorners(100))).into(itemView.avatar)

            itemView.gallery.onFlingListener = null

            var snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(itemView.gallery)

            itemView.gallery.adapter = FeedGalleryAdapter(context, post.gallery, adapter)

            itemView.gallery.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            itemView.gallery.setOnClickListener {
                Log.i("SocialCotemig", "Clicou")
            }

            itemView.like.setOnClickListener {
                listener.like(post)
            }

        }

    }

    interface FeedAdapterListener {
        fun like(post: Post)
        fun likePhoto(postImage: PostImage)
    }

}