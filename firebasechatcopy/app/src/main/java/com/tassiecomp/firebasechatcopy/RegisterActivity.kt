package com.tassiecomp.firebasechatcopy


import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val email = email_editText_register.text.toString()
        val password = password_editText_register.text.toString()

        Log.d("MainActivity", "Email is " + email)
        Log.d("MainActivity", "Password is " + password)

        register_button_register.setOnClickListener {
            performRegister()
        }
        already_have_account_text_view.setOnClickListener {
            Log.d("MainActivity", "Try to show login activity")

            //launch the login activity somehow
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        selectphoto_button_register.setOnClickListener {
            Log.d("MainActivity", "try to show photo Selector")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)

        }

    }

    var selectedPhotoUri: Uri? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            //procced and check what the selected image was...
            Log.d("registerActivity", "Photo was selected")

            selectedPhotoUri = data.data //it tells where the image is stored

            Log.d("registerActivity","selectedPhotoUri: $selectedPhotoUri")

            val bitmap = MediaStore.Images.Media.getBitmap(
                contentResolver,
                selectedPhotoUri
            ) //anything that drawn in android is bitmap
            Log.d("registerActivity","bitmap: $bitmap")

            val bitmapDrawable = BitmapDrawable(bitmap)
            Log.d("registerActivity","bitmapDrawableL $bitmapDrawable")

            selectphoto_button_register.setImageBitmap(bitmap)
            selectphoto_button_register.alpha = 0f
        }
    }

    private fun performRegister() {
        val email = email_editText_register.text.toString()
        val password = password_editText_register.text.toString()

        Log.d("Login", "Attempt login with email/pw: $email/***")

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/password", Toast.LENGTH_SHORT).show()
            return// go back to
        }
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password) //it load string from edittexts
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener // exception(when it fail) return  top of add oncomplete listener

                //else if successful
                Log.d(
                    "RegisterActivity",
                    "Successfully created user with uid: ${it.result?.user?.uid}"
                )

                uploadImageToFirebaseStorage()
            }
            .addOnFailureListener {
                Toast.makeText(this, "lease enter text in email/password", Toast.LENGTH_SHORT)
                    .show()
                Log.d("RegisterActivity", "Failed to create user: ${it.message}")
            }
    }

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return

        val filename = UUID.randomUUID().toString()
        //uploading image to firebase
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("RegisterActivity", "Successfully uploaded Image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d("RegisterActivity","File Location: $it") //it gives file location

                    saveUserToFireDatabase(it.toString())
                }
            }
            .addOnFailureListener{
                //do some logging here

            }
    }

    private fun saveUserToFireDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, username_editText_register.text.toString(), profileImageUrl)

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("RegisterActivity","Finally we saved the user to firebase Database")

                val intent = Intent(this, LatestMessagesActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
    }
}

class User(val uid:String ,val username:String, val profileImageUrl:String)