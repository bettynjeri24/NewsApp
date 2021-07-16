package com.eclecticsIntern.newsApp.utils

import java.io.IOException

class ApiException(message: String):IOException(message)

class NetWorkException(message: String):IOException(message)