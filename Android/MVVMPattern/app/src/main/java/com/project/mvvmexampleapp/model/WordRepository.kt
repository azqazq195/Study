package com.project.mvvmexampleapp.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class WordRepository(application: Application) {
    private val wordDao: WordDao?
    private val allWords: LiveData<List<Word>>?

    // init 이란
    init {
        val db = WordRoomDatabase.getInstance(application)
        wordDao = db?.wordDao()
        allWords = wordDao?.getAllWords()
    }

    fun insertWord(word: Word) {
        InsertAsyncTask(wordDao!!).excute(word)
    }

    fun deleteWord(word: Word){
        DeleteAsyncTask(wordDao!!).excute(word)
    }

    fun deleteAllWords() {
        DeleteAllAsyncTask(wordDao!!).excute()
    }

    fun getAllWords(): LiveData<List<Word>> {
        return allWords!!
    }

    fun getWordByName(name: String): Word? {
        val allWordsList = allWords?.value?.toList()

        allWordsList?.iterator()?.forEach {
            if(it.name == name){
                return it
            }
        }
        return null
    }

    private class InsertAsyncTask(private val dao: WordDao): AsyncTask<Word, Void, Void>() {
        override fun doInBackground(vararg params: Word): Void? {
            dao.insertWord(params[0])
            return null
        }
    }
}