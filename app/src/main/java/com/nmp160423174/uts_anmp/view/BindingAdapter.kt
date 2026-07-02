package com.nmp160423174.uts_anmp.view

import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.nmp160423174.uts_anmp.R

@BindingAdapter("tools:srcCompat")
fun setIcon(view: ImageView, resId: Int) {
    view.setImageResource(resId)
}


private val iconList = intArrayOf(
    R.drawable.baseline_fitness_center_24,
    R.drawable.baseline_menu_book_24,
    R.drawable.baseline_school_24,
    R.drawable.baseline_water_drop_24
)

@BindingAdapter("android:selectedItemPosition")
fun setSelectedIcon(spinner: Spinner, iconResId: Int) {
    val position = iconList.indexOf(iconResId)

    if (position != -1 && spinner.selectedItemPosition != position) {
        spinner.setSelection(position)
    }
}