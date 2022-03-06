package com.asthiseta.submission1madedicoding.home

import androidx.lifecycle.*
import com.asthiseta.core.data.Resource
import com.asthiseta.core.domain.model.Item
import com.asthiseta.core.domain.usecase.ItemUseCase


class HomeVM (itemUseCase: ItemUseCase):ViewModel(){
    private var name : MutableLiveData<String> = MutableLiveData()

    fun setForSearch(query : String){
        if (name.value == query){
            return
        }
        name.value = query
    }

    val item : LiveData<Resource<List<Item>>> = Transformations
        .switchMap(name){
            itemUseCase.getAllKos(it).asLiveData()
        }
}