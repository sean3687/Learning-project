package com.tassiecomp.kotlinmessanger

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.progressDialog

class SigninActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 1

    private val signInProviders = listOf(AuthUI.IdpConfig.EmailBuilder()
        .setAllowNewAccounts(true)
        .setRequireName(true)
        .build()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        account_sign_in.setOnClickListener{
            val intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(signInProviders)
                .setLogo(R.drawable.fui_done_check_mark)
                .build()
            startActivityForResult(intent,RC_SIGN_IN)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode ==RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if(resultCode == Activity.RESULT_OK) {
                val progressDialog = indeterminateProgressDialog("Setting up your Account")
                //TODO: Initialize current user in Firebase
                startActivity(intentFor<MainActivity>().newTask().clearTask))// this code make we don't go to the signin Activity, after it is finished
                progressDialog.dismiss()
                }

                else if(resultCode == Activity.RESULT_CANCELED) {
                    if (response == null) return

                    when (response.error?.errorCode){
                        ErrorCodes.NO_NETWORK ->
                            longSnackbar(constraint_layout, "No network")
                        ErrorCodes.UNKNOWN_ERROR ->
                            longSnackbar(constraint_layout, "Unknown Error")
                    }
                }
            }
        }
    }
}