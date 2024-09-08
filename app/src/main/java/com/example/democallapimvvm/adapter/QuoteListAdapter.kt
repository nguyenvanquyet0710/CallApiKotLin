package com.example.democallapimvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.democallapimvvm.databinding.AdsLayoutBinding
import com.example.democallapimvvm.databinding.ListItemBinding
import com.example.democallapimvvm.model.Quote

class QuoteListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_CONTENT = 1
        const val TYPE_ADS = 2
    }

    private var quoteList: List<Quote>? = null

    inner class ContentViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class AdsViewHolder(bindingAds: AdsLayoutBinding) :
        RecyclerView.ViewHolder(bindingAds.root)

    fun setQuoteList(quoteList: List<Quote>?) {
        this.quoteList = quoteList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_CONTENT) {
            val binding =
                ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ContentViewHolder(binding)
        } else {
            val bindingAds =
                AdsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdsViewHolder(bindingAds)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_CONTENT) {
            val contentHolder = holder as ContentViewHolder
            val quote = quoteList?.get(position)
            contentHolder.binding.tvName.text = quote?.author
            contentHolder.binding.tvCapital.text = quote?.content
            contentHolder.binding.tvRegion.text = quote?.tags.toString()
        } else {
            val adsHolder = holder as AdsViewHolder
        }
    }


    override fun getItemCount(): Int {
        return quoteList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 1 || position == 3) {
            TYPE_ADS
        } else {
            TYPE_CONTENT
        }
    }
}
