package com.fastholf.templine

/**
 * Created by fastholf on 15/05/2017.
 */
class Repository {
    val LINK = "https://api.darksky.net/forecast/<>/37.8267,-122.4233"

    fun getResponse(): String {
        return LINK
    }
}
