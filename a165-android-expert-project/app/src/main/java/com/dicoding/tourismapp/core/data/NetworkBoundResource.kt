package com.dicoding.tourismapp.core.data

import android.annotation.SuppressLint
import com.dicoding.tourismapp.core.data.source.remote.network.ApiResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.flow.*

@SuppressLint("CheckResult")
abstract class NetworkBoundResource<ResultType, RequestType>{

    private val result : Flow<Resource<ResultType>> = flow{
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if(shouldFetch(dbSource)){
            emit(Resource.Loading())
            when(val apiResponse = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }else{
            emitAll(loadFromDB().map{Resource.Success(it)})
        }
    }



    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    fun asFlow() : Flow<Resource<ResultType>> = result
}