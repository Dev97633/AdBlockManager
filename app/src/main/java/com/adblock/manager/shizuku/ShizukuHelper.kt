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
            val binder: IBinder = SystemServiceHelper.getSystemService("package") ?: return
            val pm = IPackageManager.Stub.asInterface(ShizukuBinderWrapper(binder))

            val userId = Shizuku.getUid() // Android 12+ required

            pkgs.forEach { pkg ->
                pm.setApplicationEnabledSetting(
                    pkg,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    0,
                    userId,
                    "com.adblock.manager"
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
