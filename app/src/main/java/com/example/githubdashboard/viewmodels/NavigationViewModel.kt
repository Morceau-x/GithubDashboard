package com.example.githubdashboard.viewmodels

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.githubdashboard.web.generators.ServiceGenerator.REDIRECT_URI
import com.example.githubdashboard.web.data.SearchData
import com.example.githubdashboard.repositories.GithubDataRepository

class NavigationViewModel(private val savedState: SavedStateHandle): ViewModel() {
    enum class NavigationState {
        SEARCH,
        DASHBOARD
    }

    private val repository = GithubDataRepository()

    private val _navigate = MutableLiveData<NavigationState>()
    val navigate: LiveData<NavigationState>
    get() = _navigate

    val searchResults: LiveData<SearchData>
    get() = repository.searchResults

    init {
        (savedState.get<Int>("currentState") as NavigationState?)?.run {
            _navigate.postValue(this)
        }
    }

    fun searchUser(data: String) {
        repository.searchUser(data)
    }

    fun parseIntent(intent: Intent) {
        val uri: Uri? = intent.data
        uri.toString().run{
            this.startsWith(REDIRECT_URI) && uri?.getQueryParameter("code")?.run {
                (uri.getQueryParameter("state")?.toInt() as NavigationState?)?.run {
                    _navigate.postValue(this)
                }

                true
            }?:false
        }
    }
}