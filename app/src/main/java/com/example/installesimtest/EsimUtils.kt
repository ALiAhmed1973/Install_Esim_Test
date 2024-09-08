package com.example.installesimtest

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.euicc.DownloadableSubscription
import android.telephony.euicc.EuiccManager
import android.widget.Toast
import androidx.annotation.RequiresApi

object EsimUtils{
    @RequiresApi(Build.VERSION_CODES.P)
    fun activateEsim(context: Context, activationCode: String) {
        val euiccManager = context.getSystemService(Context.EUICC_SERVICE) as EuiccManager

        if (euiccManager.isEnabled) {
            val subscription = DownloadableSubscription.forActivationCode(activationCode)
            val intent = Intent("test_Esim").setPackage(context.packageName)
            val callbackIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

            euiccManager.downloadSubscription(
                subscription,
                true, // switchAfterDownload
                callbackIntent
            )
        } else {
            Toast.makeText(context, "device not Supported", Toast.LENGTH_SHORT).show()
        }
    }
}