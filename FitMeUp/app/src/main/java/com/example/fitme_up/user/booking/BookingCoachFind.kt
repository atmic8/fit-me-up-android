package com.example.fitme_up.user.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.bookingAdapter.FindCoachAdapter
import com.example.fitme_up.user.dataset.CoachListData

class BookingCoachFind : Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
//    private lateinit var skipBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_coach_find, container, false)

//        skipBtn = view.findViewById(R.id.skipCoachBtn)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = requireView().findViewById(R.id.coachRecycleList)

        viewManager = LinearLayoutManager(context)
        recyclerView.layoutManager = viewManager

        val dataList2 = listOf(
            CoachListData("Coach 1", "Badminton", "Jakarta Selatan", "081122334455"),
            CoachListData("Coach 2", "Badminton", "Jakarta Selatan", "081573462455"),
            CoachListData("Coach 3", "Badminton", "Jakarta Selatan", "081124582455"),
            CoachListData("Coach 4", "Futsal", "Jakarta Selatan", "081123572455"),
            CoachListData("Coach 5", "Badminton", "Jakarta Pusat", "081124583475"),
            CoachListData("Coach 6", "Badminton", "Jakarta Barat", "081124347900"),
            CoachListData("Coach 7", "Badminton", "Jakarta Pusat", "08115673453"),
            CoachListData("Coach 8", "Futsal", "Jakarta Timur", "081123452355")
        )
        viewAdapter = FindCoachAdapter(dataList2, this)
        recyclerView.adapter = viewAdapter

//        skipBtn.setOnClickListener {
//            val userFrag = BookingVenueFind()
//            val tran = fragmentManager?.beginTransaction()
//            tran?.replace(R.id.fragment_cont, userFrag)?.commit()
//            tran?.addToBackStack(null)
//        }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//    }

//    override fun onButtonClick() {
//        val frag = BookingVenueFind()
//        val tran = fragmentManager?.beginTransaction()
//        tran?.replace(R.id.fragment_cont, frag)?.commit()
//        tran?.addToBackStack(null)
//    }
}