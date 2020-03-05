package com.example.githubdashboard.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.githubdashboard.R
import com.example.githubdashboard.viewmodels.NavigationViewModel

class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel: NavigationViewModel by activityViewModels()
        val searchButton: Button? = view?.findViewById(R.id.button_search)
        val input: EditText? = view?.findViewById(R.id.search_input)

        input?.setOnEditorActionListener { textView: TextView, i: Int, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH)
                trySearchUser(context, viewModel, textView.text)
            false
        }?.also {
            searchButton?.setOnClickListener { _: View ->
                trySearchUser(context, viewModel, input.text)
            }
        }
    }

    private fun trySearchUser(context: Context?, viewModel: NavigationViewModel, text: CharSequence?) {
        when {
            !text.isNullOrBlank() -> viewModel.searchUser(text.toString())
            else -> Toast.makeText(context, R.string.could_not_search, Toast.LENGTH_LONG).show()
        }
    }
}
