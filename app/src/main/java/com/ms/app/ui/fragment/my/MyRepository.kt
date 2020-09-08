package com.ms.app.ui.fragment.my

import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MyRepository @Inject constructor() {

    fun sayHello(): Observable<String> {
        return Observable
            .just("Hello, world!")
            .delay(2, TimeUnit.SECONDS)
    }
}