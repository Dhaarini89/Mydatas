package com.android.example.exercise2.adapter

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.exercise2.MainActivity
import com.android.example.exercise2.NextActivity
import com.android.example.exercise2.R
import com.android.example.exercise2.viewmodel.DataViewModel
import com.android.example.exercise2.viewmodel.entries
import java.nio.file.Files.delete

class CustomAdapter(val mylist : List<DataViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>()
{
    val isEnable  = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context)
           .inflate(R.layout.card_view_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val DataListModel = mylist[position]
        holder.textView.text = DataListModel.text
        holder.layoutView.setBackgroundColor(DataListModel.layoutcolor)
        holder.layoutView.setOnClickListener{
            val intent =Intent(it.context,NextActivity::class.java)
          it.context.startActivity(intent)
        }
        holder.layoutView.setOnLongClickListener{
             if (!isEnable) {
                class callback: ActionMode.Callback
                {
                    @Override
                    override fun onCreateActionMode(actionMode: ActionMode, menu: Menu): Boolean {

                        return true
                    }

                   override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
                        // Called each time the action mode is shown
                        return false
                    }

                   override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
                        // Called when the user selects a contextual menu item

                        return true
                    }


                    override  fun onDestroyActionMode(actionMode: ActionMode) {

                    }
                }

                //it.startActionMode(callback)
            } else {

            }

            return@setOnLongClickListener true

        }

        if ( entries.score > 0 && holder.textView.text.trim() =="நிறங்கள்")
            holder.imageView.visibility = View.INVISIBLE
    }


    override fun getItemCount(): Int {
       return mylist.size
    }

    class ViewHolder(dataView : View) : RecyclerView.ViewHolder(dataView)
    {
        val layoutView : LinearLayout = dataView.findViewById(R.id.layout)
        val imageView : ImageView =dataView.findViewById(R.id.image_lock)
        val textView : TextView = dataView.findViewById(R.id.text)

    }
}