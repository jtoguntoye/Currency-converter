package com.e.currencyconverter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment(R.layout.fragment_home), AdapterView.OnItemSelectedListener{

    val mainViewModel:MainViewModel by viewModels()
    lateinit var  spinner1: Spinner
    lateinit var spinner2: Spinner



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner1 = view.findViewById(R.id.spinner_from_currency)
        spinner2 = view.findViewById(R.id.spinner_to_currency)



        setUpCurrencySpinners()

        view.findViewById<Button>(R.id.button_convert).setOnClickListener {

        }
    }

    private fun setUpCurrencySpinners() {

        //get list of currencies
        mainViewModel.supportedCurrencyList.observe(viewLifecycleOwner, Observer {currencylist->
            currencylist?.let {

            when (currencylist) {
                is Resource.Success -> {
                    val currencySpinnerarrayAdapter :ArrayAdapter<String> = ArrayAdapter<String>(
                        requireContext(),
                    R.layout.item_spinner,
                    currencylist.data!!.toList()
                    )
                    currencySpinnerarrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner1.adapter = currencySpinnerarrayAdapter
                    spinner2.adapter = currencySpinnerarrayAdapter
                    spinner1.onItemSelectedListener = this
                    spinner2.onItemSelectedListener = this
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), currencylist.message, Toast.LENGTH_SHORT).show()
                }

            }

        }} )

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
         var selectedItem = parent?.getItemAtPosition(position)
        val spinner = parent as Spinner
        if(spinner.id == R.id.spinner_from_currency) {
        textView_from_currency.text = selectedItem as CharSequence
        }
        else if(spinner.id == R.id.spinner_to_currency) {
            textView_to_currency.text = selectedItem as CharSequence
        }
    }
}