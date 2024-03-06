package com.dgomesdev.mycv.model

data class Project(
    val description: String = "",
    val link: String = "",
    val projectName: String = "",
    val stacks: List<String> = emptyList()
)