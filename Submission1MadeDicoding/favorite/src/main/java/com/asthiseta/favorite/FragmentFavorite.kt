package com.asthiseta.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asthiseta.core.data.Resource
import com.asthiseta.core.ui.ItemAdapter
import com.asthiseta.di.favoriteModule
import com.asthiseta.favorite.databinding.FragmentFavoriteBinding
import com.asthiseta.misc.ShowStates
import com.asthiseta.submission1madedicoding.R
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ViewModelParameter
import org.koin.android.viewmodel.koin.getViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

class FragmentFavorite : Fragment() , ShowStates {

    private lateinit var bindingFav : FragmentFavoriteBinding
    private lateinit var favAdapter : ItemAdapter
    private val favoriteVM : FavoriteVM by sharedGraphViewModel(com.asthiseta.submission1madedicoding.R.id.main_navigation)

    private inline fun<reified VM : ViewModel> Fragment.sharedGraphViewModel(
        @IdRes navGraphId : Int,
        qualifier: Qualifier? = null,
        noinline parameters : ParametersDefinition? = null
    ) = lazy {
        val store = findNavController().getViewModelStoreOwner(navGraphId).viewModelStore
        getKoin().getViewModel(ViewModelParameter(VM::class, qualifier, parameters, store))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = getString(com.asthiseta.submission1madedicoding.R.string.favorite_txt)
        bindingFav = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        loadKoinModules(favoriteModule)
        return bindingFav.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favAdapter = ItemAdapter(arrayListOf()){name, iv ->
            findNavController().navigate(
                FragmentFavoriteDirections.actionFavoriteToDetailFragment(name),
                FragmentNavigatorExtras(iv to name)
            )
        }

        bindingFav.recyclerFav.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = favAdapter
        }
        observeFav()
    }
    override fun favLoading(bindingFav: FragmentFavoriteBinding?) {
        bindingFav?.apply {
            errorLayoutFav.mainNotFound.visibility = visible
            progressBar.apply {
                trackColor = getColor(context, R.color.orange)
                visibility = visible
            }
            recyclerFav.visibility = visible
        }
    }

    override fun favSuccess(bindingFav: FragmentFavoriteBinding?) {
        bindingFav?.apply {
            errorLayoutFav.mainNotFound.visibility = visible
            //TODO progress
            recyclerFav.visibility = visible
        }
    }

    override fun favError(bindingFav: FragmentFavoriteBinding?, message: String?) {
        bindingFav?.apply {
            errorLayoutFav.apply {
                mainNotFound.visibility = visible
                emptyText.text = message ?: resources.getString(R.string.empty_data)
            }
            //TODO Progress
            recyclerFav.visibility = gone
        }
    }

    private fun observeFav() {
        favLoading(bindingFav)
        favoriteVM.favoriteItem.observe(viewLifecycleOwner){
            it.let {
                if(!it.isNullOrEmpty()){
                    favSuccess(bindingFav)
                    favAdapter.setData(it)
                }else{
                    favError(bindingFav,
                    message = getString(R.string.empty_data))
                }
            }
        }
    }
}