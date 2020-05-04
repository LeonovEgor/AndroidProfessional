package com.anikin.aleksandr.simplevocabulary.viewmodel

import io.reactivex.rxjava3.core.Observable

interface Interactor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}