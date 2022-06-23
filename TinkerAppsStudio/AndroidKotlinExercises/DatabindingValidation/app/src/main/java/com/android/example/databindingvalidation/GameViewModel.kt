package com.android.example.databindingvalidation

import android.graphics.Typeface
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var finaldetail: String = ""
    private val _score = MutableLiveData(0)
    private val _currentWordCount = MutableLiveData(0)
    private val _currentScrambledWord = MutableLiveData<String>()
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String
    private lateinit var levelslist: List<String>
    private var _levels = MutableLiveData<String>()
    private val _test = MutableLiveData<String>()

    val test : LiveData<String>
        get() = _test
    val levels: LiveData<String>
        get() = _levels

    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord

    val score: LiveData<Int>
        get() = _score

    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    init {
        _test.value ="1"
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    private fun getNextWord() {
        _currentWordCount.value = (_currentWordCount.value)?.inc()
        var levels = "நிறங்கள்"
        when (levels) {
            "நிறங்கள்" -> {
                levelslist = allWordsListLevel1
            }
            "உணவு பொருட்கள்" -> {
                levelslist = allWordsListLevel2
            }
            "உடல் பாகங்கள்" -> {
                levelslist = allWordsListLevel3
            }
            "பழங்கள் மற்றும் காய்கறிகள்" -> {
                levelslist = allWordsListLevel4
            }
            "உயிரினங்கள்" -> {
                levelslist = allWordsListLevel5
            }
            "உணவு பொருட்கள்" -> {
                levelslist = allWordsListLevel6
            }
            "அன்றாட வீட்டு உபயோக பொருட்கள்" -> {
                levelslist = allWordsListLevel7
            }
            "அரசியல் அறிவியல்" -> {
                levelslist = allWordsListLevel8
            }
            "கணினி அறிவியல்" -> {
                levelslist = allWordsListLevel9
            }
        }
        currentWord = levelslist.random()
        var word = currentWord.toCharArray()
        while (String(word).equals(currentWord, false)) {
            var wordint =IntArray(word.size)
            for (i in 0..word.size-1)
            {
                wordint[i] = word[i].code
            }
            val wordintlist =wordint.toList()
            val finallist =wordintlist.zipWithNext() { s1, s2 -> checkvowels(s1,s2) }
            val shufflevalue =finallist.shuffled()
            for (  i in 0..shufflevalue.size-1  )
            {
                if (shufflevalue[i] !="")
                    finaldetail+=shufflevalue[i]
            }

            word = finaldetail.toCharArray()
            finaldetail=""
        }
        if (wordsList.contains(currentWord)) {
            _currentWordCount.value = (_currentWordCount.value)?.dec()
            getNextWord()

        } else
            _currentScrambledWord.value = String(word)
        _test.value = _test.value+"1"
        Log.d("nextword",_test.value.toString())
        wordsList.add(currentWord)
    }

  /*  fun funshuffle(tempword: CharArray): String {

        var twoD = arrayOf<Array<Int>>()
        var shuffledtwoD = arrayOf<Array<Int>>()
        val tamilvowels =
            listOf(3006, 3007, 3008, 3009, 3010, 3014, 3015, 3016, 3018, 3019, 3020, 3021)
        val tempWord = tempword
        val tempint = IntArray(tempWord.size)

        for (i in 0..tempWord.size - 1) {
            tempint[i] = tempWord[i].toInt()
        }
        for (i in 0..tempint.size - 1) {
            var temparray = arrayOf<Int>()
            for (j in 0..1) {
                temparray += 0
            }
            twoD += temparray
        }
        var j = 0
        for (i in 0..tempint.size - 1) {
            if (tempint[i] in tamilvowels) twoD[j - 1][1] = tempint[i]
            else {
                twoD[j][0] = tempint[i]
                j = j + 1
            }
        }

        twoD.shuffle()
        var strformat: String = ""
        for (array in twoD) {
            if (array[0] != 0) {
                for (value in array) {
                    strformat += String.format("%s", value.toChar())

                }

            }
        }


        return strformat
    } */

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }

    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    fun checkvowels(s1: Int, s2: Int ) : String
    {
        val tamilvowels = listOf(3006, 3007, 3008, 3009, 3010, 3014, 3015, 3016, 3018, 3019, 3020, 3021)
        if(s1 in tamilvowels)
        {
            return ""
        }
        if (s2 in tamilvowels)
        {
            var temp: String = s1.toChar().toString()
            temp = temp + s2.toChar().toString()
            return temp
        }
        else
            return s1.toChar().toString()
    }


}