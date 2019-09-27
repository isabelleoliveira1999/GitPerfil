package com.isa.silva.teste.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class owner(  @SerializedName("avatar_url")
                   @Expose
                   val avatar_url: String?,

                   @SerializedName("login")
                   @Expose
                   val login: String?,

                   @SerializedName("gists_url")
                   @Expose
                   val gists_url: String?)
