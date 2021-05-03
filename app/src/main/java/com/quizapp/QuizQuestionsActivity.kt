package com.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
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
        buttonSubmit.setOnClickListener(this)
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
            R.id.buttonSubmit -> {
                // If nothing is selected as an answer than move to the next question
                if (mySelectedOptionPosition == 0) {
                    myCurrentPosition++

                    when {
                        myCurrentPosition <= myQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            Toast.makeText(
                                this,
                                "You have successfully completed the quiz!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                // If user has selected an answer...
                else {
                    val question = myQuestionsList?.get(myCurrentPosition - 1)

                    // If selected answer is wrong change the view to red
                    if(question!!.correctAnswer != mySelectedOptionPosition) {
                        answerView(mySelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }

                    // No matter what display the correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    // Check if it is the last question to change the submit button text accordingly
                    if(myCurrentPosition == myQuestionsList!!.size) {
                        buttonSubmit.text = getString(R.string.finishButtonText)
                    } else {
                        buttonSubmit.text = getString(R.string.nextQuestionButtonText)
                    }

                    // Reset the selected option
                    mySelectedOptionPosition = 0
                }
            }
        }
    }

    // Function that arranges the content of the question and answers
    private fun setQuestion() {

        val question = myQuestionsList!![myCurrentPosition - 1]

        defaultOptionsView()

        // Check if the quiz is over or not
        if(myCurrentPosition == myQuestionsList!!.size) {
            buttonSubmit.text = getString(R.string.finishButtonText)
        } else {
            buttonSubmit.text = getString(R.string.submitButtonText)
        }

        // Progress bar
        progressBar.progress = myCurrentPosition
        tv_progress.text = "$myCurrentPosition" + "/" + progressBar.max

        tvQuestionId.text = question!!.question
        ivImage.setImageResource(question.image)
        tvOptionOne.text = question.optionOne
        tvOptionTwo.text = question.optionTwo
        tvOptionThree.text = question.optionThree
        tvOptionFour.text = question.optionFour
    }

    // Function to arrange the default view of the choices
    // Creates a textView array and adds the choices, than change the view for each answer
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

    // Function to change the background color of the correct and wrong answer
    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tvOptionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                tvOptionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                tvOptionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                tvOptionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    // Function to change the view of the selected option
    // @Param: tv --> textView of the chosen answer
    // @Param: selectedOptionNum --> index of the chosen answer
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