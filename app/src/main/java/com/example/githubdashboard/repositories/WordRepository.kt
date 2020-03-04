package com.example.githubdashboard.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.githubdashboard.data.daos.WordDao
import com.example.githubdashboard.data.entities.Word

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepository(private val wordDao: WordDao) {
    companion object {
        private const val TAG = "WordRepository"
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: Word) {
        Log.d(TAG, "Inserting word in database through WordDao")
        wordDao.insert(word)
    }
}