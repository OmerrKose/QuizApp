package com.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import org.w3c.dom.Text

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var myCurrentPosition: Int = 1
    private var myQuestionsList: ArrayList<Questions>? = null
    private var mySelectedOptionPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        myQuestionsList = Constants.getQuestions()

        setQuestion()

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
    }

    private fun setQuestion() {
        myCurrentPosition = 1
        val question = myQuestionsList!![myCurrentPosition - 1]

        defaultOptionsView()

        progressBar.progress = myCurrentPosition
        tv_progress.text = "$myCurrentPosition" + "/" + progressBar.max

        tvQuestionId.text = question!!.question
        ivImage.setImageResource(question.image)
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        options.add(0, tvOptionOne)
        options.add(1, tvOptionTwo)
        options.add(2, tvOptionThree)
        options.add(3, tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(tvOptionOne, 1)
            }
            R.id.tvOptionTwo -> {
                selectedOptionView(tvOptionTwo, 2)
            }
            R.id.tvOptionThree -> {
                selectedOptionView(tvOptionThree, 3)
            }
            R.id.tvOptionFour -> {
                selectedOptionView(tvOptionFour, 4)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mySelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.seletcted_option_border_bg
        )
    }
}