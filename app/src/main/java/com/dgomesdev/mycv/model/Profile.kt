package com.dgomesdev.mycv.model

data class Profile(
    val aboutMe: String = "Delivering assertive solutions through software development",
    val hardSkills: List<String> = emptyList(),
    val photo: String = "",
    val softSkills: List<String> = emptyList(),
    val title: String = "Software developer"
)