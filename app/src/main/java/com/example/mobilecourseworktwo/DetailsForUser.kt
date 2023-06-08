package com.example.mobilecourseworktwo;

import android.app.AlertDialog
import android.content.Context


object DetailsForUser {
    fun DisplayError(title: String, message: String, stackTrace: String, context: Context){
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage("$message Error: $stackTrace")
        alertDialogBuilder.setPositiveButton("OK"){dialog,which ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    fun DisplayProgress(title: String,message: String, context:Context){
        val alertDialogBuilder= AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") {dialog, which->
            dialog.dismiss()
        }
        val altDlg = alertDialogBuilder.create()
        altDlg.show()

        }
}


