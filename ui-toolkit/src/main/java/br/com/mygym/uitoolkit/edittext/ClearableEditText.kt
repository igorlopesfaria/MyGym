package br.com.mygym.uitoolkit.edittext

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import br.com.mygym.uitoolkit.R
import textChanged

class ClearableEditText @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = R.attr.uitoolkit_clearableEditText
) : LinearLayoutCompat(context, attrs, defStyleAttr) {

    val editText: EditText by lazy { findViewById<EditText>(R.id.editText) }
    val clearButton: ImageButton by lazy { findViewById<ImageButton>(R.id.clearButton) }
    val cancelButton: Button by lazy { findViewById<Button>(R.id.cancelButton) }

    private var cancelButtonClickListener: (() -> Unit)? = null
    private var textChangedListener: ((String) -> Unit)? = null
    private var clearButtonClickListener: (() -> Unit)? = null
    init {
        inflate(context, R.layout.edittext_clearable, this)
        initComponent()
    }

    private fun initComponent() {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.uitoolkit_clearableEditText, defStyleAttr,
            R.style.MyGym_Widget_EditText_ClearableEditText
        )

        val focusable = typedArray.getBoolean(R.styleable.uitoolkit_clearableEditText_uitoolkit_focusable, true)
        val enabled = typedArray.getBoolean(R.styleable.uitoolkit_clearableEditText_uitoolkit_enabled, true)
        val showCancelButton = typedArray.getBoolean(R.styleable.uitoolkit_clearableEditText_uitoolkit_show_cancel_button, false)

        editText.apply {
            isEnabled = enabled
            isFocusable = focusable
            textChanged {
                if (hasFocus()) {
                    clearButton.visibility =
                        if (TextUtils.isEmpty(text)) View.INVISIBLE else View.VISIBLE
                    textChangedListener?.invoke(text.toString())
                }
            }
            setOnFocusChangeListener { view, b ->
                if (b) {
                    if (editText.text.isNotEmpty())
                        shouldShowClearButton(true)
                    else
                        shouldShowClearButton(false)
                }
            }
        }
        editText.clearFocus()
        clearButton.apply {
            setOnClickListener{ clearText() }
        }

        cancelButton.apply {
            setOnClickListener { cancelButtonClickListener?.invoke() }
        }

        if (showCancelButton) {
            cancelButton.visibility = View.VISIBLE
            editText.requestFocus()
        } else {
            editText.setOnFocusChangeListener { _, hasFocus ->
                shouldShowCancelButton(hasFocus)
            }
        }

        typedArray.recycle()
    }

    fun clearText() {
        editText.text.clear()
        if (clearButtonClickListener != null) {
            clearButtonClickListener?.invoke()
        }
    }

    fun shouldShowCancelButton(shouldShow:Boolean) {
        if(shouldShow) {
            cancelButton.visibility = View.VISIBLE
        } else {
            cancelButton.visibility = View.GONE
            editText.clearFocus()
        }
    }

    fun shouldShowClearButton(shouldShow:Boolean) {
        if(shouldShow) {
            clearButton.visibility = View.VISIBLE
        } else {
            clearButton.visibility = View.GONE
        }
    }


    fun initListeners(cancelButtonClickListener: (() -> Unit)? = null, textChangedListener: ((String) -> Unit)? = null,  clearButtonClickListener: (() -> Unit)? = null) {
        this.cancelButtonClickListener = cancelButtonClickListener
        this.textChangedListener = textChangedListener
        this.clearButtonClickListener = clearButtonClickListener
    }
}
