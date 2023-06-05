package com.example.farming.ui.main

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androidstudy.daraja.BuildConfig
import com.androidstudy.daraja.Daraja
import com.androidstudy.daraja.callback.DarajaResult
import com.androidstudy.daraja.util.Environment
import com.androidstudy.daraja.util.TransactionType
import com.example.farming.R
import com.example.farming.databinding.FragmentMpesaPaymentBinding
import java.util.*


class MpesaPaymentFragment : Fragment() {

    private lateinit var daraja:Daraja
    private lateinit var binding:FragmentMpesaPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMpesaPaymentBinding.inflate(inflater,container,false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initializing daraja
       daraja = getDaraja()

        accessToken()
    }

    private fun getDaraja(): Daraja {
        return Daraja.builder("vdGFxeLAnSG4kbE5A4JM8faaF0BULt62", "oLAV7OPavgaPtGP1")
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
            Toast.makeText(context?.applicationContext,"Your token refreshed! Try again...",Toast.LENGTH_SHORT).show()
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
                        Toast.makeText(context?.applicationContext,"Material Payment Successful"+result.ResponseDescription,Toast.LENGTH_SHORT).show()
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