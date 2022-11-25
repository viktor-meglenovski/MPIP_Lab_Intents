package com.example.lab_intents.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_intents.R

class ActivitiesViewAdapter(private val data: MutableList<String>) :
    RecyclerView.Adapter<ActivitiesViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView
        private var currentString: String? = null
        init {
            textView = view.findViewById(R.id.txtOutput)
        }

        fun bind(currentString:String) {
            this.currentString = currentString
            this.textView.text = currentString
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}