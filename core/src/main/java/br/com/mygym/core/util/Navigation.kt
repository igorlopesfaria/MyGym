package br.com.mygym.core.util

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.net.Uri
import android.os.Bundle
import androidx.annotation.StringRes
import br.com.mygym.core.R

fun Context.navigateTo(path: String, bundle: Bundle? = null, shouldCleanActivities: Boolean? = null) {

    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(getString(R.string.deep_link_format, path))
    ).apply {
        bundle?.let { putExtras(it) }
    }

    if (shouldCleanActivities != null && shouldCleanActivities == true) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK)
    }

    this.startActivity(intent)
}

fun Context.navigateTo(@StringRes pathResId: Int, bundle: Bundle? = null, shouldCleanActivities: Boolean? = null) =
    navigateTo(getString(pathResId), bundle, shouldCleanActivities)
