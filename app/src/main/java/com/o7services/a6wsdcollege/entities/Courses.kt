package com.o7services.a6wsdcollege.entities

data class Courses(
val name: String,
val subCourses: List<String>,
val duration: String,
val entryLevelQualification: String,
val medium: String
)

