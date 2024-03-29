package com.example.farming.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.farming.databinding.FragmentDeliveryReportMaterialBinding


class DeliveryReportMaterialFragment : Fragment() {

    private lateinit var binding: FragmentDeliveryReportMaterialBinding

   // private val sms: SmsManager? = SmsManager.getDefault()
   // private lateinit var sentPendingIntent: PendingIntent
    // private lateinit var deliveredPendingIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryReportMaterialBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnspecifiedImmutableFlag", "IntentReset")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.hide()

        binding.btnReportDelivery.setOnClickListener {
            Toast.makeText(requireActivity(),"SMS feature coming soon....",Toast.LENGTH_SHORT).show()
        }
        /**
        sentPendingIntent =
            PendingIntent.getBroadcast(requireContext(), 0, Intent("SMS_SENT_ACTION"), 0)
        deliveredPendingIntent =
            PendingIntent.getBroadcast(requireContext(), 0, Intent("SMS_DELIVERED_ACTION"), 0)

        binding.btnReportDelivery.setOnClickListener {
            sms?.sendTextMessage(
                "+254740495903",
                null,
                "Delivered! Your farm product has been delivered successfully...Please confirm by entering your secret code",
                sentPendingIntent,
                deliveredPendingIntent
            )
        }
        */

        // Email reporting
        binding.emailReporting.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/email"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("stephenmuindi241@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Farm input Delivery Status")
            intent.putExtra(Intent.EXTRA_TEXT, "Your farm input is being delivered within 3 days")
            startActivity(Intent.createChooser(intent, "e-AgriWallet App"))
        }
    }
}