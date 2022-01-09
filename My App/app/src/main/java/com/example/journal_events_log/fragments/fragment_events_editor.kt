package com.example.journal_events_log.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.journal_events_log.R
import com.example.journal_events_log.adapters.JournalAdapter
import com.example.journal_events_log.models.EventsLog
import java.text.SimpleDateFormat
import java.util.*

class fragment_events_editor : Fragment() {
    var formatDate = SimpleDateFormat("ля ля ля")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events_redactor, container, false)

        val nameEd: EditText = view.findViewById(R.id.ED_Name_Editor)
        val description1: EditText = view.findViewById(R.id.ET_Description1_Editor)
        val description2: EditText = view.findViewById(R.id.ED_Detail_Editor)
        val startDateEvent: TextView = view.findViewById(R.id.TV_Start_Event)
        val endDateEvent: TextView = view.findViewById(R.id.Tv_End_Editor)
        val user: EditText = view.findViewById(R.id.ED_User_Editor)

        val saveBtn: Button = view.findViewById(R.id.save_btn)
        val backBtn: Button = view.findViewById(R.id.back_btn)

        val events = requireArguments().getSerializable("EVENTS") as EventsLog

        nameEd.setText(events.name)
        description1.setText(events.description1)
        description2.setText(events.description2)
        startDateEvent.text = (events.startDateEventsLog)
        endDateEvent.text = (events.endDateEventsLog)
        user.setText(events.UserText)

        startDateEvent.setOnClickListener{
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    startDateEvent.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH))

            datePicker.show()
        }

        endDateEvent.setOnClickListener {
            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, monthOfYear)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val date = formatDate.format(selectDate.time)
                    endDateEvent.text = date.toString()
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH))

            datePicker.show()
        }

        saveBtn.setOnClickListener {
            for(item in events){
                if (events.id == item.id){
                    item.name = nameEd.text.toString()
                    item.description1 = description1.text.toString()
                    item.description2 = description2.text.toString()
                    item.startDateEventsLog = startDateEvent.text.toString()
                    item.endDateEventsLog = endDateEvent.text.toString()
                    item.UserText = user.text.toString()

                    Toast.makeText(activity, "Note update successfully", Toast.LENGTH_SHORT).show()

                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.container_fragment, journal_fragment())?.commit()

                    Log.d("@@@@", "Update notes: ${item}")
                }
            }
        }

        backBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container_fragment, journal_fragment())?.commit()

        }

        return view

    }
 }
