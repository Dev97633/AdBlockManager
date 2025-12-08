package com.adblock.manager.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import com.adblock.manager.databinding.ActivityMainBinding
import com.adblock.manager.root.RootHelper
import com.adblock.manager.vpn.AdVpnService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AppListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AppListAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.applyBlockButton.setOnClickListener {
            applyAdblock()
        }
    }

    private fun applyAdblock() {
        val selectedPackages = adapter.getSelectedPackages()

        when {
            RootHelper.isRootAvailable() -> {
                RootHelper.applyRootRules(selectedPackages)
            }

            else -> {
                // Fallback to VPN
                startService(Intent(this, AdVpnService::class.java))
            }
        }
    }
}
