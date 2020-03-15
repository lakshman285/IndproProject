package com.example.indproproject.modules.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.indproproject.Constants
import com.example.indproproject.R
import com.example.indproproject.databinding.ActivityFactDetailsBinding
import kotlinx.android.synthetic.main.activity_fact_details.*
import org.jetbrains.anko.toast

/**
 * this activity is used to edit fact details title and description
 */
class FactDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factDetailsBinding : ActivityFactDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_fact_details)
        setSupportActionBar(tbFactDetails)
        val intent = intent
        factDetailsBinding.title = intent.getStringExtra(Constants.TITLE)
        factDetailsBinding.description = intent.getStringExtra(Constants.DESCRIPTION)

        btnSave.setOnClickListener {
            if(etFactTitle.text.isNotEmpty() and etFactDescription.text.isNotEmpty()){
                toast(getString(R.string.txt_fact_details_success_message))
                finish()
            }else {
                toast(getString(R.string.txt_fact_details_error_message))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
