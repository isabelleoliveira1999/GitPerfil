package com.isa.silva.teste.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class git(@SerializedName("description")
                       @Expose
                       val description: String?,

               @SerializedName("id")
                       @Expose
                       val id: Int?,

               @SerializedName("files")
                       @Expose
                       val files: files?)
