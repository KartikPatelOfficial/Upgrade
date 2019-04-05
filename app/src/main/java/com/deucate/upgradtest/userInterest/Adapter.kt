package com.deucate.upgradtest.userInterest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deucate.upgradtest.R
import com.deucate.upgradtest.model.Tag
import kotlinx.android.synthetic.main.card_tag.view.*

class Adapter(
    private val tags: ArrayList<Tag>,
    private val clickListener: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context!!).inflate(R.layout.card_tag, parent, false))

    override fun getItemCount(): Int = tags.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = tags[position].name
        holder.cardView.setOnClickListener {
            clickListener(position)
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView = view.cardTagTextView!!
    val cardView = view.cardTagCardView!!
}