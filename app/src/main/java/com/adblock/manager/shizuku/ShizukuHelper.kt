package com.adblock.manager.shizuku

import android.content.Context
import android.content.pm.PackageManager
import android.os.IBinder
import rikka.shizuku.Shizuku
import rikka.shizuku.ShizukuBinderWrapper
import rikka.shizuku.ShizukuSystemServerClient
import android.content.pm.IPackageManager

object ShizukuHelper {

    fun initialize(context: Context) {
        if (Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
            Shizuku.requestPermission(1000)
        }
    }

    fun isShizukuAvailable(): Boolean {
        return Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    }

    fun applyRules(pkgs: List<String>) {
        if (!isShizukuAvailable()) return

        try {
            // Get system_server binder
            val binder: IBinder = ShizukuSystemServerClient.getSystemService("package")
                ?: return

            // Wrap binder
            val wrapped = ShizukuBinderWrapper(binder)

            // Create privileged PackageManager instance
            val pm = IPackageManager.Stub.asInterface(wrapped)

            // Disable packages
            pkgs.forEach { pkg ->
                pm.setApplicationEnabledSetting(
                    pkg,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    0,
                    0,
                    "com.adblock.manager"
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
