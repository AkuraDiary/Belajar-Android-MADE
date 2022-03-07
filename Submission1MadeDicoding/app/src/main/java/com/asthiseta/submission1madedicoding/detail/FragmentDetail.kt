package com.asthiseta.submission1madedicoding.detail

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
import com.asthiseta.core.ui.ItemAdapter
import com.asthiseta.submission1madedicoding.R
import com.asthiseta.submission1madedicoding.databinding.FragmentHomeBinding
import com.asthiseta.submission1madedicoding.home.FragmentHomeDirections
import com.asthiseta.submission1madedicoding.home.HomeVM
import com.asthiseta.submission1madedicoding.misc.ShowStates
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ViewModelParameter
import org.koin.android.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

class FragmentDetail : Fragment() , ShowStates{


}