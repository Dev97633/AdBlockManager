package com.adblock.manager.vpn

import android.net.VpnService
import android.os.ParcelFileDescriptor

class AdVpnService : VpnService() {

    private var vpnInterface: ParcelFileDescriptor? = null

    override fun onCreate() {
        super.onCreate()

        val builder = Builder()
        builder.setSession("AdBlock VPN")
        builder.addAddress("10.0.0.2", 24)
        builder.addDnsServer("8.8.8.8")

        vpnInterface = builder.establish()
    }

    override fun onDestroy() {
        vpnInterface?.close()
        super.onDestroy()
    }
}
