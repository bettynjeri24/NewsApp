package com.eclecticsIntern.newsApp.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.RestrictsSuspension

object Coroutines {

    fun io(work: suspend () -> Unit) = CoroutineScope(Dispatchers.IO).launch {
        work()
    }

    fun main(work: suspend () -> Unit)= CoroutineScope(Dispatchers.Main).launch {
        work()
    }
}

