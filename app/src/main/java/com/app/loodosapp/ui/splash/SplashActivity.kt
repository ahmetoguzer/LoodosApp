package com.app.loodosapp.ui.splash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.app.loodosapp.R
import com.app.loodosapp.ui.main.MainActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.android.synthetic.main.activity_splash.*;

/**
 * Created by Ahmet Oguz Er on 19.10.2019
 */
class SplashActivity : AppCompatActivity(), SplashMvp.View {

    private var mPresenter: SplashMvp.Presenter? = null
    private var mFirebaseRemoteConfig: FirebaseRemoteConfig? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mFirebaseRemoteConfig = returnFirebaseRemoteConfig()
        mPresenter = SplashPresenter(this)
        mPresenter!!.getRemoteConfig()
        displaySplashMessage()
    }

    override fun displaySplashMessage() {
        welcomeTextView.setTextColor(Color.parseColor(mFirebaseRemoteConfig!!.getString("text_color")))
        welcomeTextView.textSize = mFirebaseRemoteConfig!!.getValue("text_size").asDouble().toFloat()
        welcomeTextView.text = mFirebaseRemoteConfig!!.getString("text_str")
    }

    override fun openNextActivity() {
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            startActivity(intent)
        }, 3000)
    }

    override fun returnFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance()
    }

}
