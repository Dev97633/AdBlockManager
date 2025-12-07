package com.adblock.manager.ui

import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adblock.manager.databinding.ItemAppBinding
import com.adblock.manager.model.AppModel

class AppListAdapter(private val context: Context) :
    RecyclerView.Adapter<AppListAdapter.ViewHolder>() {

    private val appList = mutableListOf<AppModel>()

    init {
        loadApps()
    }

    private fun loadApps() {
        val pm = context.packageManager
        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)

        apps.forEach {
            val name = pm.getApplicationLabel(it).toString()
            val pkg = it.packageName
            appList.add(AppModel(name, pkg))
        }
    }

    inner class ViewHolder(val binding: ItemAppBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAppBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = appList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = appList[position]

        holder.binding.appName.text = app.appName
        holder.binding.packageName.text = app.packageName
        holder.binding.checkbox.isChecked = app.isSelected

        holder.binding.checkbox.setOnCheckedChangeListener { _, checked ->
            app.isSelected = checked
        }
    }

    fun getSelectedPackages(): List<String> =
        appList.filter { it.isSelected }.map { it.packageName }
}
