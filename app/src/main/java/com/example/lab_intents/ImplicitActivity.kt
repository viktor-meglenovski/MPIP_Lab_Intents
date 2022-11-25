package com.example.lab_intents

import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_intents.adapters.ActivitiesViewAdapter

class ImplicitActivity : AppCompatActivity() {

    private lateinit var listView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)

        listView = findViewById(R.id.activitiesListView)

        listView.adapter = ActivitiesViewAdapter(loadData())

    }

    private fun loadData():MutableList<String> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val resolveInfos: List<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)
        val list :MutableList<String> = ArrayList()
        for (app in resolveInfos) {
            list.add(app.activityInfo.name)
        }
        return list
    }
}