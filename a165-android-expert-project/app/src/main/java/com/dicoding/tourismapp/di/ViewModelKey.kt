package com.dicoding.tourismapp.di

import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

@MustBeDocumented

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_GETTER
)

annotation class ViewModelKey(val value : KClass<out ViewModel>)