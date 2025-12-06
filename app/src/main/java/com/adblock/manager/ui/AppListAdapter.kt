package com.adblock.manager.ui
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adblock.manager.databinding.ItemAppBinding

class AppListAdapter(private val context: Context):RecyclerView.Adapter<AppListAdapter.ViewHolder>() {
    private val apps=context.packageManager.getInstalledApplications(0)
    private val selected=mutableSetOf<String>()
    inner class ViewHolder(val bind:ItemAppBinding):RecyclerView.ViewHolder(bind.root)
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int)=
        ViewHolder(ItemAppBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    override fun getItemCount()=apps.size
    override fun onBindViewHolder(holder:ViewHolder,pos:Int) {
        val app=apps[pos]
        holder.bind.appName.text=app.loadLabel(context.packageManager)
        holder.bind.packageName.text=app.packageName
        holder.bind.checkbox.setOnCheckedChangeListener(null)
        holder.bind.checkbox.isChecked=selected.contains(app.packageName)
        holder.bind.checkbox.setOnCheckedChangeListener { _, checked ->
            if(checked) selected.add(app.packageName) else selected.remove(app.packageName)
        }
    }
    fun getSelectedPackages()=selected.toList()
}
