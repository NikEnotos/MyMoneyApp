package com.example.mymoney

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import android.util.Log
import com.example.mymoney.databinding.LogInBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit
import android.content.Intent

class LogInActivity : AppCompatActivity() {
    //Variables--------------------------------------------------------
    private lateinit var binding: LogInBinding


    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken?=null
    private var mVerificationId: String? = null

    lateinit var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var firebaseAuth: FirebaseAuth

    //progress dialog

    //verification state change callbacks, verification completed, verification failed, code sent etc.
    private val LogTag = "MAIN_LOG"
    //---------------------------------------------------------------------------


    //---------------------------------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        //init, setup progress dialog


        //set layers visibility
        binding.phoneLayer.visibility = View.VISIBLE
        binding.codeLayer.visibility = View.GONE

        binding.phoneContinueBtn.setOnClickListener{

            val phoneNumber = binding.phoneBox.text.toString().trim()

            if(TextUtils.isEmpty(phoneNumber))
            {
                Toast.makeText(this@LogInActivity,"Please enter phone number", Toast.LENGTH_SHORT).show()
            } else {
                verifyPhone(phoneNumber)
            }
        }
        binding.resendCodeTv.setOnClickListener {
            //input phone number
            val phoneNumber = binding.phoneBox.text.toString().trim()
            //validate phone number
            if (TextUtils.isEmpty(phoneNumber)) {
                Toast.makeText(this@LogInActivity, "Please enter phone number", Toast.LENGTH_SHORT).show()
            } else
            {
                resendVerificationCode(phoneNumber, forceResendingToken!!)
            }
        }
        binding.codeSubmitBtn.setOnClickListener {
            //input verification code
            val code = binding.codeBox.text.toString().trim()
            if (TextUtils.isEmpty(code)) {
                Toast.makeText(
                    this@LogInActivity,
                    "Please enter verification code",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                verifyPhoneNumberWithCode(mVerificationId, code)
            }
        }
    }

    //---------------------------------------------------------------------------

    //Functions------------------------------------------------------------------
    private fun verificationCallbacks()
    {
        mCallBacks = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(LogTag, "onVerificationCompleted:$credential")

                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.w(LogTag, "onVerificationFailed", p0)
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(verificationId, token)
                mVerificationId = verificationId
                forceResendingToken = token
                Log.d(LogTag, "onCodeSent:$verificationId")

                binding.phoneLayer.visibility = View.VISIBLE
                binding.codeLayer.visibility = View.VISIBLE
            }
        }
    }
    private fun verifyPhone(phone: String)
    {


        Log.d(LogTag, "startPhoneNumberVerification: $phone")

        verificationCallbacks()
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(20, TimeUnit.SECONDS)  // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(mCallBacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential)
    {
        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener{
                Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, Expenses::class.java))
                finish()
            }
            .addOnFailureListener()
            {

                Toast.makeText(this, "Verification code is wrong", Toast.LENGTH_LONG).show()
            }
    }
    private fun resendVerificationCode(phone: String, token:PhoneAuthProvider.ForceResendingToken){

        Log.d(LogTag, "resendVerificationCode: $phone")

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallBacks)
            .setForceResendingToken(token)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private fun verifyPhoneNumberWithCode(verificationId: String?, code:String) {

        Log.d(LogTag, "resendVerificationCode: $verificationId, $code")

        var credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }
}