package com.example.taskapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val fragmentActivity: FragmentActivity) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    private var data: List<DataClass> = listOf()

    fun setData(newData: List<DataClass>) {
        data = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_list_item, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

       val button =  holder.itemView.findViewById<Button>(R.id.btn_edit)

        button.setOnClickListener {
            val fragment = UpdateFragment()
            replaceFragment(fragment,item)

        }


    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun replaceFragment(fragment: Fragment, dataClass: DataClass) {
        var bundle =  Bundle()
        bundle.putInt("id",dataClass.id)
        bundle.putString("title",dataClass.title)
        bundle.putString("description",dataClass.body)
        fragment.arguments = bundle
        fragmentActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.tv_name)
        private val mailTextView: TextView = itemView.findViewById(R.id.tv_mail)
        private val button: Button = itemView.findViewById(R.id.btn_edit)



        fun bind(item: DataClass) {
            nameTextView.text = item.title
            mailTextView.text = item.body
        }



    }
}