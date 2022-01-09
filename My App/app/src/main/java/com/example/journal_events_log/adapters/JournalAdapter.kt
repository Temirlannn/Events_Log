package com.example.journal_events_log.adapters

import android.content.Context
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.journal_events_log.R
import com.example.journal_events_log.fragments.fragment_events_editor
import com.example.journal_events_log.models.EventsLog

class JournalAdapter(private var array: ArrayList<EventsLog>, private val context: FragmentActivity) :

        RecyclerView.Adapter<JournalAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                val name: TextView = view.findViewById(R.id.TV_Name_Events)
                val description1: TextView = view.findViewById(R.id.TV_Description1)
                val description2: TextView = view.findViewById(R.id.TV_Detail_Description)
                val startDateEvent: TextView = view.findViewById(R.id.TV_Start_Date)
                val endDateEvent: TextView = view.findViewById(R.id.TV_End_Date)
                val user: TextView = view.findViewById(R.id.TV_User)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.events_log_cell_fragments, parent, false)

                return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: JournalAdapter.ViewHolder, position: Int) {

                val item = array[position]
                holder.name.text = item.name
                holder.description1.text = item.description1
                holder.description2.text = item.description2
                holder.startDateEvent.text = item.startDateEventsLog
                holder.endDateEvent.text = item.endDateEventsLog
                holder.user.text = item.UserText

                holder.itemView.setOnClickListener {
                        val bundle = Bundle()
                        bundle.putSerializable("EVENTS", item)

                        context.supportFragmentManager.beginTransaction()
                                .replace(
                                        R.id.container_fragment,
                                        fragment_events_editor::class.java,
                                        bundle
                                )
                                .addToBackStack(null).commit()
                }

        }

        override fun getItemCount(): Int {

                return array.size

        }
}
