package com.example.journal_events_log.models

import java.io.Serializable

data class EventsLog(
    var id: Int,
    var name: String ?= null,
    var description1: String ?= null,
    var description2: String ?= null,
    var startDateEventsLog: String ?= null,
    var endDateEventsLog: String ?= null,
    var UserText: String ?= null,
) : Serializable
