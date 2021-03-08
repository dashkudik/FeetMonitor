package dashkudov.feetmonitor.presentation.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.feetmonitor.R

class SettingsButton
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val iconView: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.settings_view_button, this)

        var dividerMarginStart = 0f
        var text: String? = String()
        var icon: Drawable? = null
        if (attrs != null) {
            val arr = context.obtainStyledAttributes(attrs, R.styleable.SettingsButton)
            text = arr.getString(R.styleable.SettingsButton_android_text)
            dividerMarginStart = arr.getDimension(R.styleable.SettingsButton_dividerMarginStart, 0f)
            icon = arr.getDrawable(R.styleable.SettingsButton_android_icon)
            arr.recycle()
        }

        val textView = findViewById<TextView>(R.id.settings_view_button)
        textView.text = text

        iconView = findViewById(R.id.settings_view_button_icon)
        if (icon != null) {
            iconView.visibility = View.VISIBLE
            iconView.setImageDrawable(icon)
        }

        val dividerHeight = resources.getDimensionPixelSize(R.dimen.all_divider_height)
        val dividerView = findViewById<View>(R.id.settings_view_divider)
        val params = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        params.height = dividerHeight
        params.bottomToBottom = textView.id
        params.marginStart = dividerMarginStart.toInt()
        dividerView.layoutParams = params
    }

    fun setIconResource(@DrawableRes icon: Int) {
        iconView.visibility = View.VISIBLE
        iconView.setImageResource(icon)
    }

    fun showSwitch(checked: Boolean, listener: CompoundButton.OnCheckedChangeListener) {
        val switch = findViewById<SwitchCompat>(R.id.settings_view_switch)
        switch.visibility = View.VISIBLE
        switch.isChecked = checked
        switch.setOnCheckedChangeListener(listener)
    }
}