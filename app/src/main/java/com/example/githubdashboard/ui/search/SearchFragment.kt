package com.example.githubdashboard.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.githubdashboard.R

class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val input: EditText? = view?.findViewById(R.id.search_input)
        Log.i("TEST", "SETUP")
        Log.i("TEST", input.toString())
        input?.run {
            this.setOnEditorActionListener { textView: TextView, i: Int, _ ->
                if (i == EditorInfo.IME_ACTION_SEARCH)
                    Log.i("TEST", textView.text.toString())
                false
            }
        }
    }
}
