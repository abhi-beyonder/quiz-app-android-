package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_qustion.*

class QuizQustionActivity : AppCompatActivity() , View.OnClickListener{

    private var mCurrentPosition:Int = 1;
    private var mQuestionsList: ArrayList<Qustion>?= null;
    private var mSelectedOptionPosition:Int = 0;
    private var mCorrectAnswer:Int =0;
    private var mUserName:String? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_qustion)
        mUserName=intent.getStringExtra(Consatnts.USER_NAME)

//-----------------this is will give all questions from constants.kt file---------------

        mQuestionsList = Consatnts.getQuestion()
        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion(){
        //-----------------this is will give all questions from constants.kt file---------------

        val question= mQuestionsList!![mCurrentPosition-1]
        defaultOptionsView()

        if(mCurrentPosition==mQuestionsList!!.size){
            btn_submit.text="FINISH"

        }else{
            btn_submit.text="SUBMIT"
        }

        //---------------set the things in xml view-----------------
        progressBar.progress= mCurrentPosition
        tv_progress.text="$mCurrentPosition"+ "/" + progressBar.max
        tv_question.text= question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text=question.optionOne
        tv_option_two.text=question.optiontwo
        tv_option_three.text=question.optionthree
        tv_option_four.text=question.optionfour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,tv_option_one)
        options.add(1,tv_option_two)
        options.add(2,tv_option_three)
        options.add(3,tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7a8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg )
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one->{selectedOptionView(tv_option_one,1)}
            R.id.tv_option_two->{selectedOptionView(tv_option_two,2)}
            R.id.tv_option_three->{selectedOptionView(tv_option_three,3)}
            R.id.tv_option_four->{selectedOptionView(tv_option_four,4)}
            R.id.btn_submit->{
                if(mSelectedOptionPosition==0){
                    mCurrentPosition++

                    when{
                        mCurrentPosition<=mQuestionsList!!.size->{
                            setQuestion()
                        }else->{
                             val intent = Intent(this,ResultActivity::class.java)
                        intent.putExtra(Consatnts.USER_NAME,mUserName)
                        intent.putExtra(Consatnts.CORRECT_ANSWERS,mCorrectAnswer)
                        intent.putExtra(Consatnts.TOTAL_QUESTIONS,mQuestionsList!!.size)
                        startActivity(intent)
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer!=mSelectedOptionPosition){
                        answerview(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswer++
                    }
                    answerview(question.correctAnswer,R.drawable.correct_option_border_bg)

                    if(mCurrentPosition==mQuestionsList!!.size){
                        btn_submit.text="FINISH"
                    }else{
                        btn_submit.text="Go To The Next Question"
                    }
                    mSelectedOptionPosition=0
                }
            }
        }
    }

    private fun answerview(answer: Int, drawableView:Int){
        when(answer){
            1->{tv_option_one.background=ContextCompat.getDrawable(this,drawableView)}
            2->{tv_option_two.background=ContextCompat.getDrawable(this,drawableView)}
            3->{tv_option_three.background=ContextCompat.getDrawable(this,drawableView)}
            4->{tv_option_four.background=ContextCompat.getDrawable(this,drawableView)}
        }
    }

    private fun selectedOptionView(tv:TextView,selectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition= selectedOptionNum
        tv.setTextColor(Color.parseColor("#363a43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
        }
}