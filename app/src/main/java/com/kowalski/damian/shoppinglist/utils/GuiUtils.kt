package com.kowalski.damian.shoppinglist.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.View
import com.kowalski.damian.shoppinglist.R
import org.androidannotations.annotations.EBean


@EBean(scope = EBean.Scope.Singleton)
class GuiUtils {

    fun createProgressDialog(context: Context?): Dialog {
        var dialog: Dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_progress)
        return dialog
    }

    fun createYesNoDialog(context: Context?, title: String, content: String, listener: View.OnClickListener): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(content)

        builder.setPositiveButton(context?.getString(R.string.yes)) { dialog, which ->
            dialog.dismiss()
        }

        builder.setNegativeButton(context?.getString(R.string.no)) { dialog, which ->
            dialog.dismiss()
        }

        return builder.create()
    }

}