package com.example.installesimtest

import android.R.attr.action
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.euicc.EuiccManager
import android.widget.Toast
import java.security.AccessController.getContext


class InstallEsimBroadcastReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (!action.equals("test_Esim")) {
            return
        }

        val detailedCode = intent?.getIntExtra(
            EuiccManager.EXTRA_EMBEDDED_SUBSCRIPTION_DETAILED_CODE,
            0 /* defaultValue*/
        )
        // Handle the result based on resultCode and detailedCode
        if (resultCode == EuiccManager.EMBEDDED_SUBSCRIPTION_RESULT_OK) {
            Toast.makeText(context, "installed successfully", Toast.LENGTH_SHORT).show()
        } else if (resultCode == EuiccManager.EMBEDDED_SUBSCRIPTION_RESULT_RESOLVABLE_ERROR) {
            // Handle resolvable errors, using detailedCode for specifics
            Toast.makeText(context, "error Not installed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "another error", Toast.LENGTH_SHORT).show()
        }

    }
}