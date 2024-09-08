package com.example.democallapimvvm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.democallapimvvm.adapter.QuoteListAdapter
import com.example.democallapimvvm.databinding.ActivityMainBinding
import com.example.democallapimvvm.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var quoteListAdapter: QuoteListAdapter
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.recyview.layoutManager = LinearLayoutManager(this)
        quoteListAdapter = QuoteListAdapter()
        binding.recyview.adapter = quoteListAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getLiveDataObserver().observe(this) {
            if (it != null) {
                quoteListAdapter.setQuoteList(it[0].results)
                quoteListAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeAPICall()
    }
}
