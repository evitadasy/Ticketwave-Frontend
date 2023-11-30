package com.example.bookify_frontend.model


import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.provider.BaseColumns
import android.widget.CursorAdapter
import android.widget.TextView

class SuggestionsAdapter(
    context: Context,
    cursor: Cursor,
    autoRequery: Boolean
) : CursorAdapter(context, cursor, autoRequery) {

    override fun newView(context: Context?, cursor: Cursor?, parent: android.view.ViewGroup?): android.view.View {
        return TextView(context)
    }

    override fun bindView(view: android.view.View?, context: Context?, cursor: Cursor?) {
        if (view is TextView) {
            val suggestion = cursor?.getString(cursor.getColumnIndexOrThrow("suggestion"))
            view.text = suggestion
        }
    }
}