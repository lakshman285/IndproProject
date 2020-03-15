package com.example.indproproject.modules.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.indproproject.Constants
import com.example.indproproject.R
import com.example.indproproject.adapters.FactsAdapter
import com.example.indproproject.databinding.FragmentFactsBinding
import com.example.indproproject.isInternetAvailable
import com.example.indproproject.listener.ItemClickListener
import com.example.indproproject.localDatabase.RowRepository
import com.example.indproproject.models.Row
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast


/**
 * A simple [Fragment] subclass.
 * used to display list of facts of a country
 */
class FactsFragment : Fragment(), ItemClickListener {

    private lateinit var rowViewModel: RowViewModel
    private lateinit var fragmentHomeBinding: FragmentFactsBinding
    private var rowList: List<Row> = ArrayList<Row>()
    private lateinit var factsAdapter: FactsAdapter

    private val srlFacts: SwipeRefreshLayout by lazy {
        activity!!.findViewById<SwipeRefreshLayout>(R.id.srlFacts)
    }

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
        srlFacts.setColorSchemeResources(R.color.colorPrimaryDark)
        factsAdapter = FactsAdapter(this)
        fragmentHomeBinding.adapter = factsAdapter
        displayCountryFacts()
        srlFacts.setOnRefreshListener {
            srlFacts.isRefreshing = false
            displayCountryFacts()
        }
    }

    /**
     * method used to display list of facts
     */
    private fun displayCountryFacts() {
        if (isInternetAvailable(activity!!)) {
            rowViewModel.getFacts()!!.observe(activity!!,
                Observer { facts ->
                    rowList = facts.rows
                    if(rowList.isEmpty()){
                        context!!.runOnUiThread {
                            RowRepository(activity!!).saveAllRows(rowList as ArrayList<Row>){}
                        }
                    }
                    factsAdapter.addRowList(rowList)
                    factsAdapter.notifyDataSetChanged()
                    fragmentHomeBinding.model = facts
                })

        }else {
            if(rowList.isNotEmpty()){
                context!!.runOnUiThread {
                    RowRepository(activity!!).getAllRows(){
                        rowList = it
                        factsAdapter.addRowList(rowList)
                        factsAdapter.notifyDataSetChanged()
                    }
                }
            }else {
                context!!.toast(getString(R.string.txt_please_check_your_internet_connection))
                fragmentHomeBinding.tbFacts.title = getString(R.string.txt_no_internet)
            }
        }
    }

    override fun onClickItem(obj: Any) {
        if (obj is Int) {
            context!!.startActivity(Intent(context, FactDetailsActivity::class.java)
                .putExtra(Constants.TITLE,rowList[obj].title)
                .putExtra(Constants.DESCRIPTION, rowList[obj].description))
        }
    }
}
