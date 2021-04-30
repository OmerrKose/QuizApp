package com.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        val currentPosition = 1
        val questions: Questions = Constants.getQuestions()[currentPosition - 1]

        progressBar.progress = currentPosition
        tv_progress.text = "$currentPosition" + "/" + progressBar.max

        tvQuestionId.text = questions!!.question
        ivImage.setImageResource(questions.image)
        tvOptionOne.text = questions.optionOne
        tvOptionTwo.text = questions.optionTwo
        tvOptionThree.text = questions.optionThree
        tvOptionFour.text = questions.optionFour
    }
}