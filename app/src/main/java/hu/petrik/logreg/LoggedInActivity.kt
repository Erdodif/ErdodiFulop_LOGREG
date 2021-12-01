package hu.petrik.logreg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.petrik.logreg.databinding.ActivityLoggedInBinding

class LoggedInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = ActivityLoggedInBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val db = SQLiteHelper(this)
        val user = db.getById(intent.extras!!.getInt("all"))
        bind.textViewUser.text = user!!.fullname
        bind.btnLogOut.setOnClickListener {
            FCompanion.swapActivity(this,MainActivity())
        }
    }
}