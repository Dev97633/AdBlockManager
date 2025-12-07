package com.adblock.manager.shizuku

import android.content.Context
import android.content.pm.PackageManager
import dev.rikka.shizuku.Shizuku
import dev.rikka.shizuku.OnRequestPermissionResultListener

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
        // Register listener
        Shizuku.addRequestPermissionResultListener(permissionListener)

        // Request permission if missing
        if (!isShizukuAvailable()) {
            Shizuku.requestPermission(0)
        }
    }

    fun isShizukuAvailable(): Boolean {
        return Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED
    }

    fun applyRules(pkgs: List<String>) {
        // TODO: Implement rule commands here
        // Example (Shizuku shell):
        // val process = Shizuku.newProcess(arrayOf("cmd", "package", "disable", pkg), null, null)
    }
}
