package com.isa.silva.teste.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class convert(@SerializedName("filename")
                       @Expose
                       val filename: String?,
                   @SerializedName("language")
                   @Expose
                   val language: String? )
