package com.dgomesdev.mycv.model

data class CvData(
    val education: List<Education> = emptyList(),
    val foreignLanguages: List<ForeignLanguage> = emptyList(),
    val others: List<OtherExperience> = emptyList(),
    val profile: Profile = Profile(),
    val projects: List<Project> = emptyList(),
    val workExperience: List<WorkExperience> = emptyList()
)