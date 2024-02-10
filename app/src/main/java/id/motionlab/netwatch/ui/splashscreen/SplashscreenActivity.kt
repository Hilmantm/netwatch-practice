package id.motionlab.netwatch.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import id.motionlab.netwatch.R
import id.motionlab.netwatch.ui.movie.MainActivity

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        // do preparation here
        // ex-1: if this app should use GPS, make sure GPS is on before switching to the main activity
        // ex-2: if this application is being installed for the first time, make sure the permissions required to use this application have been approved
        // etc

        Handler(Looper.getMainLooper()).postDelayed({
            val toMainActivity = Intent(this@SplashscreenActivity, MainActivity::class.java)
            startActivity(toMainActivity)
            finish()
        }, 1300)
    }
}