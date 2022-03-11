package com.asthiseta.submission1madedicoding.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.Item
import com.asthiseta.submission1madedicoding.R
import com.asthiseta.submission1madedicoding.databinding.FragmentDetailBinding
import com.shashank.sony.fancytoastlib.FancyToast
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentDetail : Fragment(){
    private lateinit var bindingDetail : FragmentDetailBinding
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
            FancyToast.makeText(
                context, "Added ${item.name} To Favorite", Toast.LENGTH_SHORT, FancyToast.SUCCESS, false
            ).show()

        }else{
            item.isFav = !isFavorite
            detailVM.deleteFav(item)
            FancyToast.makeText(
                context, "Removed ${item.name} from Favorite", Toast.LENGTH_SHORT, FancyToast.INFO, false
            ).show()

        }
        isFavorite = item.isFav!!
    }

    private fun observeDetail() {
        detailVM.detailItem(args.name).observe(viewLifecycleOwner) {
            when(it){
                is Resource.Success ->{
                    item = it.data!!

                    bindingDetail.data = it.data

                    detailVM.getDetailState(args.name)?.observe(viewLifecycleOwner) { _item ->
                        isFavorite = _item.isFav == true

                        changeFav(isFavorite)
                    }

                    bindingDetail.fabFavorite.show()
                    changeFav(isFavorite)
                    bindingDetail.fabFavorite.setOnClickListener{


                        addOrRemoveFav()
                        changeFav(isFavorite)

                    }
                }

                is Resource.Error -> {
                    bindingDetail.fabFavorite.hide()
                }

                is Resource.Loading -> {
                    bindingDetail.fabFavorite.hide()
                }
            }
        }
    }
}