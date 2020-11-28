package br.com.cotemig.socialcotemig.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.Direct
import br.com.cotemig.socialcotemig.services.RetrofitInitializer
import br.com.cotemig.socialcotemig.ui.adapters.DirectAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_direct.*
import kotlinx.android.synthetic.main.item_feed.view.*
import retrofit2.Call
import retrofit2.Response

class DirectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direct)

        back.setOnClickListener {
            finish()
        }

        getDirectMessage("daniele")
    }

    fun getDirectMessage(username: String) {

        var s = RetrofitInitializer().serviceDirect()

        var call = s.getDirect(username)

        call.enqueue(object : retrofit2.Callback<Direct> {

            override fun onResponse(call: Call<Direct>?, response: Response<Direct>?) {
                response?.let {

                    if (it.code() == 200) {

                        Glide.with(this@DirectActivity).load(it.body().avatar)
                            .apply(
                                RequestOptions().transform(RoundedCorners(100))
                            ).into(avatar)

                        user.text = it.body().user
                        name.text = it.body().name

                        directmessages.adapter = DirectAdapter(
                            this@DirectActivity, username,
                            it.body().messages
                        )

                        directmessages.layoutManager = LinearLayoutManager(
                            this@DirectActivity,
                            LinearLayoutManager.VERTICAL, false
                        )

                    }

                }
            }

            override fun onFailure(call: Call<Direct>?, t: Throwable?) {
                Toast.makeText(this@DirectActivity, "Erro", Toast.LENGTH_LONG).show()
            }
        })

    }
}