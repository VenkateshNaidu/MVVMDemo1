package com.venkygithub.mvvmdemo1.util

import java.io.IOException

class ApiExceptions(message : String) : IOException(message)
class NoInternetException(message : String) : IOException(message)