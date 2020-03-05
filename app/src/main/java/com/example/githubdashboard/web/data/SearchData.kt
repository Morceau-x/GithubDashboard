package com.example.githubdashboard.web.data

data class SearchData (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<UserData>
    )