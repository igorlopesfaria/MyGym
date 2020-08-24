package br.com.mygym.uitoolkit.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import br.com.mygym.uitoolkit.R

class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val messageTitleView: TextView by lazy { findViewById<TextView>(R.id.empty_message_title) }
    private val messageSubtitleView: TextView by lazy { findViewById<TextView>(R.id.empty_message_subtitle) }

    init {
        inflate(context, R.layout.view_empty, this)
        initAttributes(context, attrs)
    }

    fun setEmptyTitle(message: CharSequence?) {
        messageTitleView.text = message
    }

    fun setEmptySubtitle(message: CharSequence?) {
        messageSubtitleView.text = message
    }
    
    @SuppressLint("CustomViewStyleable")
    private fun initAttributes(context: Context, set: AttributeSet?) {
        val attrArray = context.obtainStyledAttributes(set, R.styleable.uitoolkit_emptyView)
        val title: CharSequence? = attrArray.getText(R.styleable.uitoolkit_emptyView_uitoolkit_title)
        val subtitle: CharSequence? = attrArray.getText(R.styleable.uitoolkit_emptyView_uitoolkit_subtitle)
        attrArray.recycle()

        setEmptyTitle(title)
        setEmptySubtitle(subtitle)
    }
}
