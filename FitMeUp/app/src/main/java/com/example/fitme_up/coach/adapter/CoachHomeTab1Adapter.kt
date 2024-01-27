package com.example.fitme_up.coach.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.OnButtonClickListener
import com.example.fitme_up.R
import com.example.fitme_up.coach.CoachRequestDetails
import com.example.fitme_up.coach.dataset.CoachingData

class CoachHomeTab1Adapter(private val myDataset: List<CoachingData>, private val parentFragment: Fragment) : RecyclerView.Adapter<CoachHomeTab1Adapter.MyViewHolder>(),
    OnButtonClickListener {

    class MyViewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.text_recycler_title)
        val textView2: TextView = itemView.findViewById(R.id.text_recycler_subtitle)
        val textView3: TextView = itemView.findViewById(R.id.text_recycler_subtitle2)
        val textView4: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right)
        val textView5: TextView = itemView.findViewById(R.id.text_recycler_subtitle_right2)

        fun bind(item: CoachingData) {
            textView1.text = item.venueName
            textView2.text = item.venueSport
            textView3.text = item.venueDomicile
            textView4.text = item.bookDate
            textView5.text = item.bookDuration.toString() + " Hour(s)"
            itemView.findViewById<Button>(R.id.btn_view_details).setOnClickListener {
                listener.onButtonClick()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list, parent, false)
        return MyViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset[position]
        holder.textView1.text = item.venueName
        holder.textView2.text = item.venueSport
        holder.textView3.text = item.venueDomicile
        holder.textView4.text = item.bookDate
        holder.textView5.text = item.bookDuration.toString() + " Hour(s)"

        holder.itemView.findViewById<Button>(R.id.btn_view_details).setOnClickListener {
            this.onButtonClick()
        }

    }

    override fun getItemCount() = myDataset.size

    override fun onButtonClick() {
        val newFragment = CoachRequestDetails()
        val transaction = parentFragment.parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_cont, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}