package hu.petrik.logreg

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import android.widget.Toast

class FCompanion {
    companion object {
        fun swapActivity(from: Activity, to: Activity, extras: Int? = null) {
            val intent = Intent(from, to::class.java)
            if (extras !== null) {
                intent.putExtra("all", extras)
            }
            from.startActivity(intent)
            from.finish()
        }

        fun showToast(context: Activity, message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun bejelentkezes(inputIdentifier: EditText, inputPassword: EditText) {
            val context = inputIdentifier.context
            val db = SQLiteHelper(context)
            val nameOrEmail = inputIdentifier.text.toString()
            val password = inputPassword.text.toString()
            if (nameOrEmail == "" || password == "") {
                showToast(context as Activity, "Minden mező kitöltése kötelező!")
            } else {
                val result = db.login(nameOrEmail, password)
                if (result > 0) {
                    swapActivity(context as Activity, LoggedInActivity(), result)
                } else {
                    showToast(context as Activity, "Sikertelen bejelentkezés!")
                }
            }
        }

        fun register(iUname: EditText, iEmail: EditText, iPassword: EditText, iFullName: EditText) {
            val context = iUname.context
            val db = SQLiteHelper(context)
            val uname = iUname.text.toString()
            val email = iEmail.text.toString()
            val password = iPassword.text.toString()
            val fullName = iFullName.text.toString()
            if (uname.isEmpty() || email.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
                showToast(context as Activity,"Minden mező megadása kötelező!")
            }
            else{
                val user = User(null,uname,email,password,fullName)
                if (db.register(user)){
                    showToast(context as Activity,"Sikeres regisztráció!")
                }
                else{
                    showToast(context as Activity, "Sikertelen regisztráció!")
                }
            }
        }
    }
}