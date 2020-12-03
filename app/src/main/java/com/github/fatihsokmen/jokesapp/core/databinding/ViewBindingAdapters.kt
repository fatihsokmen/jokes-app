package com.github.fatihsokmen.jokesapp.core.databinding

import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter

object ViewBindingAdapters {

    @BindingAdapter("visibleWhen")
    @JvmStatic
    fun setVisibility(view: View, value: Boolean) {
        view.visibility = if (value) View.VISIBLE else View.GONE
    }

    @BindingAdapter("html")
    @JvmStatic
    fun setHtml(view: TextView, value: String) {
        view.text = HtmlCompat.fromHtml(value, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }
}