package com.example.fitme_up.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fitme_up.R
import com.example.fitme_up.RetrofitClient
import com.example.fitme_up.blueprint.DataResponse
import com.example.fitme_up.blueprint.RegisterVenueOwnerInput
import com.example.fitme_up.blueprint.ValidationError
import com.example.fitme_up.viewmodel.ViewModelList
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterVenueOwnerFragment : Fragment() {

    lateinit var registerButton: Button
    var nameEditText: EditText? = null
    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null
    var confirmPasswordEditText: EditText? = null
    var dobEditText: EditText? = null

    //retrofit
    private lateinit var viewModel: ViewModelList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_venue_owner, container, false)

        nameEditText = view.findViewById(R.id.input_register_name)
        emailEditText = view.findViewById(R.id.input_register_email)
        passwordEditText = view.findViewById(R.id.input_register_password)
        confirmPasswordEditText = view.findViewById(R.id.input_register_confirm_password)
        dobEditText = view.findViewById(R.id.input_register_dob)

        registerButton = view.findViewById(R.id.btn_register_venue_owner)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RETROFIT

        registerButton.setOnClickListener {

            val register = RegisterVenueOwnerInput(
                nameEditText?.text.toString(),
                emailEditText?.text.toString(),
                passwordEditText?.text.toString(),
                confirmPasswordEditText?.text.toString(),
                dobEditText?.text.toString(),
                null,
                null,
                null,
                arrayListOf(),
                null,
                null,
                null,
                null,
                null
            )

            val fetch = RetrofitClient.instance.postRegisterVenueOwner(register)

            fetch.enqueue(object : Callback<DataResponse> {
                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show()

                    } else{
                        val errorMsg =  response.errorBody()

                        val errorResponse = Gson().fromJson(errorMsg?.charStream(), ValidationError::class.java)
                        if (errorResponse.error.message.size == 9) {
                            if (errorResponse.error.message[0].field.equals("domicileId") &&
                                errorResponse.error.message[1].field.equals("venueName") &&
                                errorResponse.error.message[2].field.equals("venueAddress") &&
                                errorResponse.error.message[3].field.equals("venueSports") &&
                                errorResponse.error.message[4].field.equals("venueSchedules") &&
                                errorResponse.error.message[5].field.equals("venueTimeOpen") &&
                                errorResponse.error.message[6].field.equals("venueTimeClose") &&
                                errorResponse.error.message[7].field.equals("venuePhotos") &&
                                errorResponse.error.message[8].field.equals("venueDescription")
                            ) {
                                val bundle = Bundle()
                                bundle.putParcelable("bundle", register)

                                val frag = RegisterVenueOwnerFragment2()
                                frag.arguments = bundle

                                val tran = fragmentManager?.beginTransaction()
                                tran?.replace(R.id.frame_register_cont, frag)
                                tran?.commit()
                                tran?.addToBackStack(null)
                            }
                        }
                        else{
                            for (i in 0..<errorResponse.error.message.size){
                                if (errorResponse.error.message[i].field.equals("fullName")){
                                    nameEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("email")){
                                    emailEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("dateBirth")){
                                    dobEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("password")){
                                    passwordEditText?.error = errorResponse.error.message[i].message
                                }else if (errorResponse.error.message[i].field.equals("password_confirmation")){
                                    confirmPasswordEditText?.error = errorResponse.error.message[i].message
                                }
                            }
                        }
                        Log.d("print", errorResponse.toString())
                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d("print", t.toString())
                }
            })
        }
    }
}