package com.adblock.manager.root
object RootHelper {
    fun isRootAvailable()=try{Runtime.getRuntime().exec("su -c id").waitFor()==0}catch(e:Exception){false}
    fun applyRootRules(pkgs:List<String>) {
        val cmd=pkgs.joinToString("\n"){ "iptables -A OUTPUT -m owner --uid-owner $it -j REJECT" }
        Runtime.getRuntime().exec(arrayOf("su","-c",cmd))
    }
}
