package hu.petrik.logreg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.petrik.logreg.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.btnVissza.setOnClickListener {
            FCompanion.swapActivity(this,MainActivity())
        }
        bind.btnRegisztracio.setOnClickListener {
            FCompanion.register(bind.inputUName,bind.inputEmail,bind.inputPassword,bind.inputFullName)
        }
    }
}