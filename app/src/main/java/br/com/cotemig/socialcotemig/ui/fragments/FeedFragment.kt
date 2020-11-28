package br.com.cotemig.socialcotemig.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.Post
import br.com.cotemig.socialcotemig.models.PostImage
import br.com.cotemig.socialcotemig.models.Story
import br.com.cotemig.socialcotemig.services.RetrofitInitializer
import br.com.cotemig.socialcotemig.ui.activities.DirectActivity
import br.com.cotemig.socialcotemig.ui.activities.MainActivity
import br.com.cotemig.socialcotemig.ui.activities.NewStoriesActivity
import br.com.cotemig.socialcotemig.ui.adapters.FeedAdapter
import br.com.cotemig.socialcotemig.ui.adapters.StoriesAdapter
import kotlinx.android.synthetic.main.fragment_feed.view.*
import retrofit2.Call
import retrofit2.Response

class FeedFragment : Fragment(), FeedAdapter.FeedAdapterListener,
    StoriesAdapter.NewStoriesViewHolderListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        view.directmessages.setOnClickListener {
            var intent = Intent(context, DirectActivity::class.java)
            startActivity(intent)
        }

        view.refresh.setOnRefreshListener {
            getFeed(view)
        }

        getFeed(view)
        getStories(view)

        return view
    }

    override fun clickNewStories() {
        var intent = Intent(context, NewStoriesActivity::class.java)
        startActivity(intent)
    }

    override fun like(post: Post) {
        Log.i("SocialCotemig", "Clicou no Like: " + post.description)
    }

    override fun likePhoto(postImage: PostImage) {
        Log.i("SocialCotemig", "Clicou na foto dentro do post: " + postImage.image)
    }

    fun getStories(view: View) {

        var activity = context as MainActivity

        var s = RetrofitInitializer().serviceFeed()
        var call = s.getStories()

        call.enqueue(object : retrofit2.Callback<List<Story>> {

            override fun onResponse(call: Call<List<Story>>?, response: Response<List<Story>>?) {

                response?.let {

                    if (it.code() == 200) {

                        view.stories.adapter =
                            StoriesAdapter(activity, response.body(), this@FeedFragment)

                        view.stories.layoutManager = LinearLayoutManager(
                            activity,
                            LinearLayoutManager.HORIZONTAL, false
                        )

                    }

                }

            }

            override fun onFailure(call: Call<List<Story>>?, t: Throwable?) {

            }
        })

    }

    fun getFeed(view: View) {

        var activity = context as MainActivity

        var s = RetrofitInitializer().serviceFeed()

        var call = s.getFeed()

        call.enqueue(object : retrofit2.Callback<List<Post>> {

            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {

                view.refresh.isRefreshing = false

                response?.let {

                    if (it.code() == 200) {

                        view.feed.adapter = FeedAdapter(
                            activity, it.body(),
                            this@FeedFragment
                        )

                        view.feed.layoutManager = LinearLayoutManager(
                            activity,
                            LinearLayoutManager.VERTICAL, false
                        )


                    }

                }

            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                view.refresh.isRefreshing = false
            }
        })


    }

}