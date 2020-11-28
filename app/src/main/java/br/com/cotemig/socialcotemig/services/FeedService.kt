package br.com.cotemig.socialcotemig.services

import br.com.cotemig.socialcotemig.models.Post
import br.com.cotemig.socialcotemig.models.Story
import retrofit2.Call
import retrofit2.http.GET

interface FeedService {

    @GET("feed")
    fun getFeed(): Call<List<Post>>

    @GET("stories")
    fun getStories(): Call<List<Story>>

}