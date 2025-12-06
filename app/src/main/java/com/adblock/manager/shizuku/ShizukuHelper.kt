package com.adblock.manager.shizuku
import android.content.Context
import rikka.shizuku.Shizuku

object ShizukuHelper {
    fun initialize(ctx:Context){
        Shizuku.addRequestPermissionResultListener { _, _ -> }
        if (!isShizukuAvailable()) Shizuku.requestPermission(0)
    }
    fun isShizukuAvailable()=
        Shizuku.checkSelfPermission()==android.content.pm.PackageManager.PERMISSION_GRANTED
    fun applyRules(pkgs:List<String>) { /* TODO */ }
}
