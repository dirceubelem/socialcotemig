package br.com.cotemig.socialcotemig.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.cotemig.socialcotemig.R
import br.com.cotemig.socialcotemig.helpers.SharedPreferencesHelper
import br.com.cotemig.socialcotemig.ui.fragments.FeedFragment
import br.com.cotemig.socialcotemig.ui.fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(FeedFragment(), "FeedFragment")

        home.setOnClickListener {
            setFragment(FeedFragment(), "FeedFragment")
        }

        search.setOnClickListener {
            setFragment(SearchFragment(), "SearchFragment")
        }

        user.setOnClickListener {
            SharedPreferencesHelper.delete(this, "user", "user")
            showLoginActivity()
        }

    }

    fun setFragment(f: Fragment, name: String) {
        // iniciando a transação para trocar de conteúdo da tela (fragment)
        val ft = supportFragmentManager.beginTransaction()
        // adicionando o objeto instanciado do fragment
        ft.replace(R.id.content, f)
        // adicionando o fragment com o nome na pilha de fragments
        ft.addToBackStack(name)
        // confirmando a troca do fragment no framelayout
        ft.commit()
    }

    fun showLoginActivity() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}