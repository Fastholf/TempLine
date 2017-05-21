package com.fastholf.templine

import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by fastholf on 15/05/2017.
 */
class RootPresenter(val repository: Repository, val view: View) {

    fun onStart() {
        repository.getResponse().observeOn(AndroidSchedulers.mainThread()).subscribe { view.showResponse(it.toString()) }
    }

    interface View {
        fun showResponse(response: String)
    }
}