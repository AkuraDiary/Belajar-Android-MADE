package com.asthiseta.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.asthiseta.core.domain.usecase.ItemUseCase

class FavoriteVM(itemUseCase: ItemUseCase) : ViewModel(){
    val favoriteItem = itemUseCase.getFavoriteItem().asLiveData()
}
