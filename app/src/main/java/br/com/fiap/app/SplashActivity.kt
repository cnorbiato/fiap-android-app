package br.com.fiap.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val TEMPO_AGUARDO_SPLASHSCREEN = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val stayLogged = preferences.getBoolean("stay_logged", false)
        if (stayLogged) {
            validateAndLog()
        } else {
            carregar()
        }

    }

    private fun validateAndLog() {
        val proximaTela = Intent(this@SplashActivity, AboutActivity::class.java)
        startActivity(proximaTela)
    }

    private fun carregar() {
        //Carrega a animacao
        val anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        anim.reset()
        ivLogo.clearAnimation()
        //Roda a animacao
        ivLogo.startAnimation(anim)
        // Chama a próxima tela após 2 segundos definido na SPLASH_DISPLAY_LENGTH
        Handler().postDelayed({
            val proximaTela = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(proximaTela)
            finish()
        }, TEMPO_AGUARDO_SPLASHSCREEN)
    }
}
