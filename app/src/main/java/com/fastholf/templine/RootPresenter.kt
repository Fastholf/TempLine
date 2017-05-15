package com.fastholf.templine

/**
 * Created by fastholf on 15/05/2017.
 */
class RootPresenter(val repository: Repository, val view: View) {

    fun onStart() {
        view.showResponse(repository.getResponse())
    }

    interface View {
        fun showResponse(response: String)
    }
}