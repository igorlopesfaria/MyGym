package br.com.mygym.uitoolkit.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import br.com.mygym.uitoolkit.R

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val loadintTextView: TextView by lazy { findViewById<TextView>(R.id.loading_text) }

    init {
        inflate(context, R.layout.view_loading, this)
        initAttributes(context, attrs)
    }

    fun setLoadingText(message: CharSequence?) {
        loadintTextView.text = message
    }

    @SuppressLint("CustomViewStyleable")
    private fun initAttributes(context: Context, set: AttributeSet?) {
        val attrArray = context.obtainStyledAttributes(set, R.styleable.uitoolkit_loadingView)
        val title: CharSequence? = attrArray.getText(R.styleable.uitoolkit_loadingView_uitoolkit_loading_text)
        attrArray.recycle()

        setLoadingText(title)
    }
}
