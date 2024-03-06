package com.dgomesdev.mycv.model

data class WorkExperience(
    val beginningDate: String = "",
    val company: String = "",
    val endDate: String = "",
    val jobTitle: String = "",
    val location: String = "",
    val summary: List<String> = emptyList()
)