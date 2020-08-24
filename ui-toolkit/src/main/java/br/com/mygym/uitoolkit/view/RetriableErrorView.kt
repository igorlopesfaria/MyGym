package br.com.mygym.uitoolkit.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import br.com.mygym.uitoolkit.R
import br.com.mygym.uitoolkit.button.LoaderButton

class RetriableErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val messageTitleView: TextView by lazy { findViewById<TextView>(R.id.error_message_title) }
    private val messageSubtitleView: TextView by lazy { findViewById<TextView>(R.id.error_message_subtitle) }
    private val retryButton: LoaderButton by lazy { findViewById<LoaderButton>(R.id.button_retry) }

    init {
        inflate(context, R.layout.view_retriable, this)
        initAttributes(context, attrs)
    }

    fun setErrorTitle(message: CharSequence?) {
        messageTitleView.text = message
    }

    fun setErrorSubtitle(message: CharSequence?) {
        messageSubtitleView.text = message
    }

    fun setButtonOnClickListener(listener: ((view: View) -> Unit)?) {
        retryButton.setOnClickListener(listener)
    }

    fun setButtonOnClickListener(listener: View.OnClickListener?) {
        retryButton.setOnClickListener(listener)
    }
    
    @SuppressLint("CustomViewStyleable")
    private fun initAttributes(context: Context, set: AttributeSet?) {
        val attrArray = context.obtainStyledAttributes(set, R.styleable.uitoolkit_retriableErrorView)
        val title: CharSequence? = attrArray.getText(R.styleable.uitoolkit_retriableErrorView_uitoolkit_title)
        val subtitle: CharSequence? = attrArray.getText(R.styleable.uitoolkit_retriableErrorView_uitoolkit_subtitle)
        attrArray.recycle()

        setErrorTitle(title)
        setErrorSubtitle(subtitle)
    }
}
