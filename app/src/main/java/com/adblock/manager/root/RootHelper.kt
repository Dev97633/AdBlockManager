package com.adblock.manager.root

import java.io.DataOutputStream

object RootHelper {

    fun isRootAvailable(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec("su")
            process.outputStream.write("exit\n".toByteArray())
            process.waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }

    fun applyRootRules(packages: List<String>) {
        try {
            val process = Runtime.getRuntime().exec("su")
            val os = DataOutputStream(process.outputStream)

            packages.forEach { pkg ->
                os.writeBytes("cmd package disable $pkg\n")
            }

            os.writeBytes("exit\n")
            os.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
