package com.example.fitme_up.user.booking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fitme_up.R
import com.example.fitme_up.user.adapter.VenueImageAdapter
import com.example.fitme_up.user.dataset.VenueImageData

class BookingVenueDetails : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var venueOrderBtn: Button
    private lateinit var venueNameText: TextView
    private lateinit var venueSportText: TextView
    private lateinit var venueDomicileText: TextView
    private lateinit var venueDescText: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_booking_venue_details, container, false)

        viewPager = view.findViewById(R.id.venuePager)
        venueOrderBtn = view.findViewById(R.id.btn_book_venue)

        venueNameText = view.findViewById(R.id.text_venue_name)
        venueSportText = view.findViewById(R.id.text_venue_sport)
        venueDomicileText = view.findViewById(R.id.text_venue_domicile)
        venueDescText = view.findViewById(R.id.text_venue_desc)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = listOf(
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1),
            VenueImageData(R.drawable.image_1)
        )

        venueNameText.text = "Tifosi"
        venueSportText.text = "Badminton"
        venueDomicileText.text = "Jakarta Timur"
        venueDescText.text = "Tifosi Badminton Venue with many facilities\n" +
                "- Food Court\n" +
                "- Parking Lot\n" +
                "- Toilet"

        venueOrderBtn.setOnClickListener() {
            val frag = BookingVenueOrder()
            val tran = fragmentManager?.beginTransaction()
            tran?.addToBackStack(null)
            tran?.replace(R.id.fragment_cont, frag)?.commit()
        }

        viewPager.adapter = VenueImageAdapter(imageList)


        val fragmentManager = activity?.supportFragmentManager
        if (fragmentManager != null) {
            val backStackCount = activity?.supportFragmentManager?.backStackEntryCount
            if (backStackCount != null) {
                if (backStackCount > 0) {

//                    Log.d("print", fragmentManager.findFragmentByTag("lfg_find_venue").toString())
                    val fm = fragmentManager.findFragmentByTag("lfg_find_venue")

                    if (fm != null) {
                        addFragmentWithTag(this, "lfg_venue_details")
                        Log.d("print", "from lfg find venue")
                        venueOrderBtn.text = "Book Venue for LFG"
//                        Toast.makeText(activity, "from lfg", Toast.LENGTH_SHORT).show()
                        //tambah logic data kalo dari lfg (masukin ke lfg DB)
                    }
                }
            }
        }
    }

    fun addFragmentWithTag(fragment: Fragment, tag: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_cont, fragment, tag)
            ?.commit()
    }

}