package com.example.weather.base

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.view.customview.SpacingDecoration
import com.google.android.material.bottomsheet.BottomSheetDialog


internal fun FragmentManager.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    this.beginTransaction()
        .add(containerViewId, fragment)
        .apply { if (addToBackStack) addToBackStack(null) }
        .commit()
}


internal fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    this.beginTransaction()
        .replace(containerViewId, fragment)
        .apply { if (addToBackStack) addToBackStack(null) }
        .commit()
}

internal fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layout, this, attachToRoot)


internal fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    liveData?.observe(this, { t -> action(t) })
}

internal fun RecyclerView.linearLayout(
    context: Context,
    spacing: Int = 0,
    noRightSpacingForFirstItem: Boolean = false,
    spanCount: Int? = 1,
    @RecyclerView.Orientation orientation: Int? = RecyclerView.VERTICAL,
    reverseLayout: Boolean? = false
) {

    layoutManager = LinearLayoutManager(context, orientation!!, reverseLayout!!)
    setHasFixedSize(true)
    addItemDecoration(
        SpacingDecoration(
            spanCount = spanCount!!,
            spacing = spacing,
            noRightSpacingForFirstItem = noRightSpacingForFirstItem,
            includeEdge = true
        )
    )
}


internal fun RecyclerView.gridLayout(
    context: Context,
    spacing: Int = 0,
    noRightSpacingForFirstItem: Boolean = false,
    spanCount: Int = 3,
    @RecyclerView.Orientation orientation: Int? = RecyclerView.VERTICAL,
    reverseLayout: Boolean? = false
) {

    layoutManager = GridLayoutManager(context, spanCount, orientation!!, reverseLayout!!)
    setHasFixedSize(true)
    addItemDecoration(
        SpacingDecoration(
            spanCount = spanCount,
            spacing = spacing,
            noRightSpacingForFirstItem = noRightSpacingForFirstItem,
            includeEdge = true
        )
    )
}

internal fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {

    this.addTextChangedListener(object : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

    })

}

internal fun BottomSheetDialog.setup(
    contentView: View,
    cancelable: Boolean = true,
    isBackgroundTransparent: Boolean = false
) {

    if (isBackgroundTransparent) {
        (contentView.parent as View).setBackgroundColor(Color.TRANSPARENT)
    }

    setContentView(contentView)
    setCancelable(cancelable)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}

internal fun View.gone() {
    this.visibility = View.GONE
}

internal fun View.hide() {
    this.visibility = View.INVISIBLE
}

internal fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.showToast(
    message: String,
    duration: Int = Toast.LENGTH_SHORT,
    gravity: Int = Gravity.TOP
) {
    Toast.makeText(this, message, duration).apply {
        setGravity(gravity, 0, 0)
        show()
    }
}