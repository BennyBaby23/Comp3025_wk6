package com.mdev.comp3025_wk6

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ContactModel(val FullName: String, val ContactNumber: String, val EmailADDRESS: String)
