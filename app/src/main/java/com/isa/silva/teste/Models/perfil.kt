package com.isa.silva.teste.Models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class perfil(@SerializedName("name")
                       @Expose
                       val name: String?,

                  @SerializedName("id")
                       @Expose
                       val id: Int?,

                  @SerializedName("language")
                  @Expose
                  val language: String?,

                  @SerializedName("owner")
                       @Expose
                       val owner: owner?)
