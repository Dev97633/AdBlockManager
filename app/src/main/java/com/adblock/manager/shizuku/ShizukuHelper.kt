package com.adblock.manager.shizuku

import android.content.Context
import android.content.pm.PackageManager
import rikka.shizuku.Shizuku
import rikka.shizuku.Shizuku.OnRequestPermissionResultListener

object ShizukuHelper {

    private val permissionListener =
        OnRequestPermissionResultListener { requestCode, grantResult ->
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                // Shizuku permission granted
            } else {
                // Permission denied
            }
        }

    fun initialize(context: Context) {
        // Register permission listener
        Shizuku.addRequestPermissionResultListener(permissionListener)

        // Request permission if not already granted
        if (!isShizukuAvailable()) {
            Shizuku.requestPermission(0)
        }
    }

    fun isShizukuAvailable(): Boolean {
        return Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    }

    fun applyRules(pkgs: List<String>) {
        pkgs.forEach { pkg ->
            try {
                Shizuku.newProcess(
                    arrayOf("cmd", "package", "disable", pkg),
                    null,
                    null
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
