package com.example.journal_events_log.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.journal_events_log.Constants.Companion.counterID
import com.example.journal_events_log.Constants.Companion.events
import com.example.journal_events_log.R
import com.example.journal_events_log.adapters.JournalAdapter
import com.example.journal_events_log.models.EventsLog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class journal_fragment : Fragment() {

    lateinit var addBtn: FloatingActionButton
    lateinit var recyclerView: RecyclerView

    var formatDate = SimpleDateFormat("ля ля ля")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_journal_fragment, container, false)

        addBtn = view.findViewById(R.id.events_add_btn)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = JournalAdapter(events, requireActivity())
        addBtn.setOnClickListener{
            addEventsDialog()
        }
        return view

    }

    private fun addEventsDialog() {

        val alert = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater.inflate(R.layout.add_events_log, null)
        alert.setView(inflater)

        val addBtn:  Button = inflater.findViewById(R.id.add_btn)
        val backBtn: Button = inflater.findViewById(R.id.back_btn)

        val name = inflater.findViewById<EditText>(R.id.edit_text)
        val description = inflater.findViewById<EditText>(R.id.ET_Description1)
        val detailDescription = inflater.findViewById<EditText>(R.id.ET_Detail_Description)
        val user = inflater.findViewById<EditText>(R.id.ED_User)
        val tvDateStartEvents = inflater.findViewById<TextView>(R.id.TV_Date_Start)
        val tvDateEndEvents = inflater.findViewById<TextView>(R.id.TV_Date_End)

        tvDateStartEvents.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    tvDateStartEvents.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH))

            datePicker.show()
        }

        tvDateEndEvents.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    tvDateEndEvents.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH))

            datePicker.show()
        }

        addBtn.setOnClickListener {
            events.add(
                EventsLog(
                    counterID,
                    name.text.toString(),
                    description.text.toString(),
                    detailDescription.text.toString(),
                    user.text.toString(),
                    tvDateStartEvents.text.toString(),
                    tvDateEndEvents.text.toString(),
                )
            )

            counterID++
            Log.d("@@@@", "Added notes: ${events}")

        }
    }
}