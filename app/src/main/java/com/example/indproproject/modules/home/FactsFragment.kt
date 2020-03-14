package com.example.indproproject.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.indproproject.R
import com.example.indproproject.adapters.FactsAdapter
import com.example.indproproject.databinding.FragmentFactsBinding
import com.example.indproproject.isInternetAvailable
import com.example.indproproject.models.Row
import kotlinx.android.synthetic.main.fragment_facts.*
import org.jetbrains.anko.toast

/**
 * A simple [Fragment] subclass.
 * used to display list of facts of a country
 */
class FactsFragment : Fragment() {

    private lateinit var rowViewModel: RowViewModel
    private lateinit var fragmentHomeBinding: FragmentFactsBinding
    private lateinit var rowList: List<Row>
    private lateinit var factsAdapter: FactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_facts,
            container,
            false
        )
        rowViewModel = ViewModelProviders.of(this).get(RowViewModel::class.java)
        return fragmentHomeBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        factsAdapter = FactsAdapter()
        fragmentHomeBinding.adapter = factsAdapter
        if (isInternetAvailable(activity!!)) {
            rowViewModel.getFacts()!!.observe(activity!!,
                Observer { facts ->
                    rowList = facts.rows
                    factsAdapter.addRowList(rowList)
                    factsAdapter.notifyDataSetChanged()
                    fragmentHomeBinding.model = facts
                })

        }else {
            context!!.toast(getString(R.string.txt_please_check_your_internet_connection))
        }
    }
}