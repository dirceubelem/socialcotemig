package br.com.cotemig.socialcotemig.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.models.Account
import br.com.cotemig.socialcotemig.services.RetrofitInitializer
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signup.setOnClickListener {
            signUpClick()
        }

        login.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun signUpClick() {

        var pass = password.text.toString()
        var conf = confirm.text.toString()

        if (pass == conf) {

            var s = RetrofitInitializer().serviceAccount()

            var account = Account()
            account.name = name.text.toString()
            account.email = email.text.toString()
            account.password = pass

            var call = s.create(account)

            call.enqueue(object : retrofit2.Callback<Account> {

                override fun onResponse(call: Call<Account>?, response: Response<Account>?) {

                    response?.let {

                        if (it.code() == 200) {
                            MaterialDialog(this@SignUpActivity).show {
                                title(R.string.success)
                                message(R.string.userCreated)
                                positiveButton(R.string.ok)
                                positiveButton {

                                    var intent =
                                        Intent(this@SignUpActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()

                                }
                            }

                        } else {
                            MaterialDialog(this@SignUpActivity).show {
                                title(R.string.title)
                                message(R.string.userExists)
                                positiveButton(R.string.ok)
                            }
                        }

                    }

                }

                override fun onFailure(call: Call<Account>?, t: Throwable?) {
                    MaterialDialog(this@SignUpActivity).show {
                        title(R.string.title)
                        message(R.string.generic_error)
                        positiveButton(R.string.ok)
                    }
                }
            })

        } else {

            MaterialDialog(this@SignUpActivity).show {
                title(R.string.title)
                message(R.string.passwordInvalid)
                positiveButton(R.string.ok)
            }

        }

    }
}