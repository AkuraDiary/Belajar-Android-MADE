package com.asthiseta.submission1madedicoding.misc

class ResourceStats<out E>(val states : MyStates, val data : E?, val message : String?) {
    companion object{
        fun <E> onLoading(data : E?) : ResourceStats<E> = ResourceStats(MyStates.IS_LOADING, data, null)
        fun <E> onSuccess(data : E) : ResourceStats<E> = ResourceStats(MyStates.IS_SUCCESS, data, null)
        fun <E> onError(data:E?, message: String) : ResourceStats<E> = ResourceStats(MyStates.IS_ERROR, data, message)
    }
}