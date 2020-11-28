package br.com.cotemig.socialcotemig.services

import br.com.cotemig.socialcotemig.models.Account
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountService {

    @POST("account/auth")
    fun auth(@Body account: Account): Call<Account>

    @POST("account")
    fun create(@Body account: Account): Call<Account>

}