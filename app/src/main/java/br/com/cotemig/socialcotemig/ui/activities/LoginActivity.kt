package br.com.cotemig.socialcotemig.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.helpers.SharedPreferencesHelper
import br.com.cotemig.socialcotemig.models.Account
import br.com.cotemig.socialcotemig.services.RetrofitInitializer
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login.setOnClickListener {
            loginClick()
        }

        signup.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun loginClick() {

        var s = RetrofitInitializer().serviceAccount()

        var account = Account()
        account.email = email.text.toString()
        account.password = password.text.toString()

        var call = s.auth(account)

        login.visibility = View.GONE

        call.enqueue(object : retrofit2.Callback<Account> {

            override fun onResponse(call: Call<Account>?, response: Response<Account>?) {

                login.visibility = View.VISIBLE

                response?.let {

                    if (it.code() == 200) {

                        saveUser(account)

                        // tudo certo - usuário autenticado

                        // salva os dados do usuário

                        var intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)

                        finish()

                    } else {

                        MaterialDialog(this@LoginActivity).show {
                            title(R.string.title)
                            message(R.string.userAndPasswordInvalid)
                            positiveButton(R.string.ok)
                        }

//                        MaterialDialog(this@LoginActivity).show {
//                            title(R.string.title)
//                            message(R.string.userAndPasswordInvalid)
//                            positiveButton(R.string.yes)
//                            positiveButton {
//                                // aqui vai implementar a acao
//                            }
//                            negativeButton(R.string.no)
//                            negativeButton {
//                                // aqui vai implemetar a acao
//                            }
//                        }


//                        Toast.makeText(
//                            this@LoginActivity,
//                            "Usuário ou senha inválidos",
//                            Toast.LENGTH_LONG
//                        ).show()

                    }

                }


            }

            override fun onFailure(call: Call<Account>?, t: Throwable?) {

                login.visibility = View.VISIBLE

                MaterialDialog(this@LoginActivity).show {
                    title(R.string.title)
                    message(R.string.generic_error)
                    positiveButton(R.string.ok)
                }
            }
        })

    }

    fun saveUser(account: Account) {

//        SharedPreferencesHelper.saveString()

        var j = JSONObject()
        j.put("email", account.email)
        j.put("password", account.password)
//        j.put("expire", Date + 3 dias)

        SharedPreferencesHelper.saveString(this, "user", "user", j.toString())

//        SharedPreferencesHelper.saveString(this, "userpreferences", "email", account.email)
//        SharedPreferencesHelper.saveString(this, "userpreferences", "password", account.password)

    }
}