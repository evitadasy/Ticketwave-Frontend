package com.example.bookify_frontend.model


import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.TextView
import com.example.bookify_frontend.R

class SuggestionsAdapter(
    context: Context,
    cursor: Cursor,
    autoRequery: Boolean
) : CursorAdapter(context, cursor, autoRequery) {

    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.item_suggestion, parent, false)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        if (view != null && cursor != null) {
            val suggestionTextView: TextView = view.findViewById(R.id.suggestionTextView)


            val suggestion = cursor.getString(cursor.getColumnIndexOrThrow("suggestion"))


            suggestionTextView.text = suggestion

        }
    }
}