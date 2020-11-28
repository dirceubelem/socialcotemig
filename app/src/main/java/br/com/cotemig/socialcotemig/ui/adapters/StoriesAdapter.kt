package br.com.cotemig.socialcotemig.ui.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.Story
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.item_newstories.view.*
import kotlinx.android.synthetic.main.item_stories.view.*
import kotlinx.android.synthetic.main.item_stories.view.image

class StoriesAdapter(
    var context: Context, var list: List<Story>,
    var listener: NewStoriesViewHolderListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            var view = LayoutInflater.from(context).inflate(R.layout.item_newstories, parent, false)
            return NewStoriesViewHolder(view)
        } else {
            var view = LayoutInflater.from(context).inflate(R.layout.item_stories, parent, false)
            return StoriesViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            (holder as NewStoriesViewHolder).bind(context, listener)
        } else {
            (holder as StoriesViewHolder).bind(context, list[position - 1])
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    class NewStoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, listener: NewStoriesViewHolderListener) {

            itemView.image.setOnClickListener {
                listener.clickNewStories()
            }

        }
    }

    interface NewStoriesViewHolderListener {
        fun clickNewStories()
    }

    class StoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(context: Context, story: Story) {

            Glide.with(context).load(story.avatar)
                .transform(RoundedCorners(19.px))
                .into(itemView.image)

            if (story.live) {
                itemView.live.visibility = View.VISIBLE
            } else {
                itemView.live.visibility = View.GONE
            }

        }

        val Int.dp: Int
            get() = (this / Resources.getSystem().displayMetrics.density).toInt()
        val Int.px: Int
            get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    }


}