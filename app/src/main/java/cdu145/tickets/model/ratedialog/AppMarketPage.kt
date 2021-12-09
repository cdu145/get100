package cdu145.tickets.model.ratedialog

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.net.Uri

class AppMarketPage(
    private val context: Context,
) {

    fun open() {
        val packageName = context.applicationInfo.packageName
        val viewMarketIntent = Intent(
            ACTION_VIEW,
            Uri.parse("market://details?id=$packageName"),
        ).apply {
            addFlags(
                FLAG_ACTIVITY_NO_HISTORY or FLAG_ACTIVITY_NEW_DOCUMENT or
                        FLAG_ACTIVITY_MULTIPLE_TASK or FLAG_ACTIVITY_NEW_TASK
            )
        }
        try {
            context.startActivity(viewMarketIntent)
        } catch (e: ActivityNotFoundException) {
            val viewUriIntent = Intent(
                ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=$packageName"),
            ).apply { addFlags(FLAG_ACTIVITY_NEW_TASK) }
            context.startActivity(viewUriIntent)
        }
    }
}