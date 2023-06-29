package com.example.farming.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import com.example.farming.R
import com.example.farming.databinding.FragmentMpesaPaymentBinding
import com.example.farming.ui.main.mpesa.api.DarajaApiClient
import com.example.farming.ui.main.mpesa.api.requests.StkPushRequest
import com.example.farming.ui.main.mpesa.api.responses.AuthorizationResponse
import com.example.farming.ui.main.mpesa.api.responses.StkPushSuccessResponse
import com.example.farming.utils.Constants
import com.example.farming.utils.Constants.BUSINESS_SHORT_CODE
import com.example.farming.utils.Constants.CALLBACKURL
import com.example.farming.utils.Constants.PARTYB
import com.example.farming.utils.Constants.PASSKEY
import com.example.farming.utils.Constants.SANDBOX_BASE_URL
import com.example.farming.utils.RegEx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


/**
 * Daraja API integration in Android app
 */

class MpesaPaymentFragment : Fragment(),View.OnClickListener {


    private var mApiClient: DarajaApiClient? = null

    var mAmount: EditText? = null
    var mPhone: EditText? = null
    var mPay: Button? = null

    //private lateinit var daraja: Daraja
    private lateinit var binding: FragmentMpesaPaymentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMpesaPaymentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        // initializing daraja
        //daraja = getDaraja()

        //accessToken()

        mAmount= view.findViewById(R.id.enterAmount)
        mPhone = view.findViewById(R.id.enterPhoneNumber)
        mPay = view.findViewById(R.id.pay)


        val consumerKey = "FG8ad21FyJDGWALcyur1437M7sZ0kQuR"
        val consumerSecret = "3rRqAkJcajHxzeur"


        mApiClient = DarajaApiClient(
            consumerKey,
            consumerSecret,
            SANDBOX_BASE_URL
        )

        mApiClient!!.setIsDebug(true)
        mPay!!.setOnClickListener(this)

        getAccessToken()

    }
    private fun getAccessToken() {
        mApiClient!!.setGetAccessToken(true)
        mApiClient!!.mpesaService()!!.getAccessToken().enqueue(object :
            Callback<AuthorizationResponse?> {
            override fun onResponse(call: Call<AuthorizationResponse?>, response: Response<AuthorizationResponse?>) {
                if (response.isSuccessful) {
                    mApiClient!!.setAuthToken(response.body()?.accessToken)
                }
            }

            override fun onFailure(call: Call<AuthorizationResponse?>, t: Throwable) {
                Log.e("MainActivity",t.printStackTrace().toString())
            }
        })
    }

    override fun onClick(v: View?) {
        if (v === mPay ) {

            val phoneNumber = mPhone!!.text.toString()
            val amount = mAmount!!.text.toString()
            performSTKPush(phoneNumber, amount)
        }
    }

    private fun performSTKPush(phoneNumber: String, amount: String) {
        val timestamp = RegEx.getTimestamp()

        val stkPush = StkPushRequest(
            businessShortCode = BUSINESS_SHORT_CODE,
            password = RegEx.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp!!)!!,
            timestamp = timestamp,
            transactionType = Constants.TransactionType.CustomerPayBillOnline,
            amount = amount,
            partyA = RegEx.sanitizePhoneNumber(phoneNumber)!! ,
            partyB = PARTYB,
            phoneNumber = RegEx.sanitizePhoneNumber(phoneNumber)!!,
            callBackURL = CALLBACKURL,
            accountReference = "LIPA NA MPESA",
            transactionDesc = "LIPA NA MPESA C2B"
        )

        mApiClient!!.setGetAccessToken(false)

        mApiClient!!.mpesaService()!!.sendPush(stkPush).enqueue(object : Callback<StkPushSuccessResponse> {
            override fun onResponse(call: Call<StkPushSuccessResponse>, response: Response<StkPushSuccessResponse>) {

                try {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            requireContext().applicationContext,
                            "Response : ${response.body().toString()}",
                            Toast.LENGTH_SHORT
                        ).show()

                        navigateToDeliveryScreen()

                        Log.d(TAG,"post submitted to API. %s")
                    } else {
                        Log.e(TAG,"Response %s")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<StkPushSuccessResponse>, t: Throwable) {
                Log.e(TAG,t.printStackTrace().toString(), httpException)

            }
    })

}

    private fun navigateToDeliveryScreen() {
        findNavController().navigate(R.id.action_mpesaPaymentFragment_to_clientDeliveryVerificationFragment)
    }

    companion object {
        val httpException : HttpException? = null
        const val TAG = "MpesaPaymentFragment"
    }
}

    /**
    private fun getDaraja(): Daraja {
        return Daraja.builder("Uku3wUhDw9z0Otdk2hUAbGZck8ZGILyh", "JDjpQBm5HpYwk38b")
            .setBusinessShortCode("174379")
            .setPassKey("bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919")
            .setTransactionType(TransactionType.CustomerBuyGoodsOnline)
            .setCallbackUrl("https://us-central1-mpesaapisecond.cloudfunctions.net/myCallbackUrl")
            .setEnvironment(Environment.SANDBOX)

            .build()
    }

    private fun pay() {
        val phoneNumber = binding.enterPhoneNumber.text.toString()
        val amountString = binding.enterAmount.text.toString()

        if (phoneNumber.isBlank() || amountString.isBlank()) {
            Toast.makeText(context?.applicationContext,"Empty fields",Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountString.toInt()
        initiatePayment(phoneNumber, amount)
    }

    private fun initiatePayment(phoneNumber: String, amount:Int) {
        val token =  getAccessToken(requireContext())

        if (token == null) {
            accessToken()
            Toast.makeText(context?.applicationContext,"You token refreshed! Try again...",Toast.LENGTH_SHORT).show()
        } else {
            // initiate payment
            daraja.initiatePayment(
                token,
                phoneNumber,
                amount.toString(),
                generateUUID(),
                "Payment"
            ) { darajaResult ->

                when (darajaResult) {
                    is DarajaResult.Success -> {
                        val result = darajaResult.value
                        Toast.makeText(context?.applicationContext,"Farm input Payment Successful"+result.ResponseDescription,Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_mpesaPaymentFragment_to_clientDeliveryVerificationFragment)
                    }
                    is DarajaResult.Failure -> {
                        val exception = darajaResult.darajaException
                        if (darajaResult.isNetworkError) {
                            Toast.makeText(context?.applicationContext,exception?.message ?: "Network error!",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context?.applicationContext,exception?.message ?: "Payment failed!",Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }
    }

    private fun accessToken() {
        // get access token
        daraja.getAccessToken { darajaResult ->

            when (darajaResult) {
                is DarajaResult.Success -> {
                    val accessToken = darajaResult.value
                    saveAccessToken(requireContext(), accessToken.access_token)
                    binding.pay.setOnClickListener { pay() }
                }
                is DarajaResult.Failure -> {
                    val darajaException = darajaResult.darajaException

                    Toast.makeText(context?.applicationContext,darajaException?.message ?: "An error occurred!",Toast.LENGTH_SHORT).show()

                    binding.pay.setOnClickListener { accessToken() }
                }
            }
        }
    }

    private fun saveAccessToken(context: Context, accessToken: String) {
        val cal = Calendar.getInstance()
        cal.add(Calendar.HOUR, 1)
        val oneHourAfter = cal.timeInMillis

        val mSettings = context.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, MODE_PRIVATE)
        val editor = mSettings.edit()

        editor.putString("accessToken", accessToken)
        editor.putLong("expiryDate", oneHourAfter)
        editor.apply()
    }

    private fun getAccessToken(context: Context): String? {
        return if (expired(context)) {
            null
        } else {
            val mSettings = context.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, MODE_PRIVATE)
            mSettings.getString("accessToken", "")
        }
    }

    private fun expired(context: Context): Boolean {
        val mSettings = context.getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, MODE_PRIVATE)
        val expiryTime = mSettings.getLong("expiryDate", 0)
        val currentTime = Calendar.getInstance().timeInMillis
        return currentTime > expiryTime
    }

    private fun generateUUID(): String =
        UUID.randomUUID().toString()

}
        */