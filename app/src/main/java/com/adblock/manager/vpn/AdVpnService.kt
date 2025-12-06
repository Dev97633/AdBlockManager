package com.adblock.manager.vpn
import android.net.VpnService
import android.os.ParcelFileDescriptor

class AdVpnService:VpnService(){
    private var vpn:ParcelFileDescriptor?=null
    override fun onStartCommand(i:android.content.Intent?,f:Int,id:Int):Int{
        startVPN()
        return START_STICKY
    }
    private fun startVPN() {
        val b=Builder()
        b.addAddress("10.10.10.1",24)
        b.addDnsServer("1.1.1.1")
        b.addRoute("0.0.0.0",0)
        vpn=b.establish()
    }
}
