package com.chslcompany.githubrepo.core.util

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class CustomErrorScreen @JvmOverloads constructor(
    context: Context,
    attrs : AttributeSet?,
    defStyleAttr : Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr){

    private lateinit var image : String
    private lateinit var userFeedback : String


}