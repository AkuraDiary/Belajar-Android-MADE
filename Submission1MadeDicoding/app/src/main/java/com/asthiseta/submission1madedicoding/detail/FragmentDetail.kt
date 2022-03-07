package com.asthiseta.submission1madedicoding.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.PagerAdapter
import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.Item
import com.asthiseta.submission1madedicoding.R
import com.asthiseta.submission1madedicoding.databinding.FragmentDetailBinding

import org.koin.android.viewmodel.ext.android.viewModel

class FragmentDetail : Fragment(){
    private lateinit var bindingDetail : FragmentDetailBinding
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var item : Item
    private var isFavorite = false

    private val args : FragmentDetailArgs by navArgs()
    private val detailVM : DetailVM by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = args.name
        bindingDetail = FragmentDetailBinding.inflate(layoutInflater, container, false)
        bindingDetail.lifecycleOwner = viewLifecycleOwner
        observeDetail()
        return bindingDetail.root
    }

    private fun changeFav(favoriteStats: Boolean) {
        if(favoriteStats){
            bindingDetail.fabFavorite.setImageResource(R.drawable.ic_fav)
        }else{
            bindingDetail.fabFavorite.setImageResource(R.drawable.ic_unfav)
        }
    }

    private fun addOrRemoveFav(){
        if(!isFavorite){
            item.isFav = !isFavorite
            detailVM.insertFav(item)
            // TODO TOAST
            isFavorite = !isFavorite
        }else{
            item.isFav = !isFavorite
            detailVM.deleteFav(item)
            // TODO TOAST
            isFavorite = !isFavorite
        }
    }

    private fun observeDetail() {
        detailVM.detailItem(args.name).observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success ->{
                    item = it.data!!
                    bindingDetail.data = it.data
                    detailVM.getDetailState(args.name)?.observe(viewLifecycleOwner) { item ->
                        isFavorite = item.isFav == true
                        changeFav(isFavorite)
                    }
                    bindingDetail.fabFavorite.show()
                }

                is Resource.Error -> {
                    bindingDetail.fabFavorite.hide()
                }

                is Resource.Loading -> {
                    bindingDetail.fabFavorite.hide()
                }
            }
            changeFav(isFavorite)
            bindingDetail.fabFavorite.setOnClickListener{
                addOrRemoveFav()
                changeFav(isFavorite)
            }
        }
    }
}