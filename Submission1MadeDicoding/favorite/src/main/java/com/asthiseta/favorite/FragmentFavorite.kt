package com.asthiseta.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asthiseta.core.data.Resource
import com.asthiseta.core.ui.ItemAdapter
import com.asthiseta.di.favoriteModule
import com.asthiseta.favorite.databinding.FragmentFavoriteBinding
import com.asthiseta.submission1madedicoding.databinding.FragmentHomeBinding
import com.asthiseta.submission1madedicoding.misc.ShowStates
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ViewModelParameter
import org.koin.android.viewmodel.koin.getViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

class FragmentFavorite : Fragment() , ShowStates{

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

    private fun observeFav() {
        //homeLoading(bindingFav) TODO
    }

    override fun homeLoading(bindingHome: FragmentHomeBinding?) {
        TODO("Not yet implemented")
    }

    override fun homeSuccess(bindingHome: FragmentHomeBinding?) {
        TODO("Not yet implemented")
    }

    override fun homeError(bindingHome: FragmentHomeBinding?, message: String?) {
        TODO("Not yet implemented")
    }
}