package hu.petrik.logreg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.petrik.logreg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.btnRegisztracio.setOnClickListener {
            FCompanion.swapActivity(this,RegisterActivity())
        }
        bind.btnBejelentkezes.setOnClickListener {
            FCompanion.bejelentkezes(bind.inputIdentifier,bind.inputPassword)
        }
    }
}