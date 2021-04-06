package com.skillbox.github.data

import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Project(
        val userName: String?,
        var id: Long,
        var name: String,
        var full_name: String?,
        var owner: User,
        var html_url: String?,
        var description: String?,
        var url: String?,
        var created_at: Date?,
        var updated_at: Date?,
        var pushed_at: Date?,
        var git_url: String?,
        var ssh_url: String?,
        var clone_url: String?,
        var svn_url: String?,
        var homepage: String?,
        var stargazers_count: Int,
        var watchers_count: Int?,
        var language: String?,
        var has_issues: Boolean,
        var has_downloads: Boolean,
        var has_wiki: Boolean,
        var has_pages: Boolean,
        var forks_count: Int?,
        var open_issues_count: Int?,
        var forks: Int?,
        var open_issues: Int?,
        var watchers: Int?,
        var default_branch: String?
)