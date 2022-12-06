package com.shokal.affirmation.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val name: String? = null,
    val email: String? = null,
    val dateOfBirth: String? = null,
    val id: String? = null,
    val mobile: String? = null,
    val image: String? = null
)