package com.isa.silva.teste.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class files(@SerializedName("convert.rb")
                       @Expose
                       val convert: convert?)
