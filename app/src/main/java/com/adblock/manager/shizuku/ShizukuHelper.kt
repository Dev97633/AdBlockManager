package com.adblock.manager.shizuku

import android.content.Context
import android.content.pm.PackageManager
import android.os.IBinder
import rikka.shizuku.Shizuku
import rikka.shizuku.ShizukuBinderWrapper
import rikka.shizuku.SystemServiceHelper
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
            // Get binder from system_server (correct API)
            val binder: IBinder = SystemServiceHelper.getSystemService("package")
                ?: return

            // Wrap binder for Shizuku
            val wrapped = ShizukuBinderWrapper(binder)

            // Convert binder → IPackageManager interface
            val pm = IPackageManager.Stub.asInterface(wrapped)

            // Disable the requested packages
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
