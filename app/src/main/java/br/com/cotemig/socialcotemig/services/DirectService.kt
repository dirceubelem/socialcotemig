package br.com.cotemig.socialcotemig.services

import br.com.cotemig.socialcotemig.models.Direct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DirectService {

    @GET("message/{user}")
    fun getDirect(@Path("user") user: String): Call<Direct>

}