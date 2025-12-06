package com.adblock.manager.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.adblock.manager.databinding.ActivityMainBinding
import com.adblock.manager.shizuku.ShizukuHelper
import com.adblock.manager.root.RootHelper
import android.content.Intent
import com.adblock.manager.vpn.AdVpnService

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AppListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter=AppListAdapter(this)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter

        binding.applyBlockButton.setOnClickListener { applyAdblock() }

        ShizukuHelper.initialize(this)
    }
    private fun applyAdblock() {
        if (RootHelper.isRootAvailable())
            RootHelper.applyRootRules(adapter.getSelectedPackages())
        else if (ShizukuHelper.isShizukuAvailable())
            ShizukuHelper.applyRules(adapter.getSelectedPackages())
        else
            startService(Intent(this, AdVpnService::class.java))
    }
}
