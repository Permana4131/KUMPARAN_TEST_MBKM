package com.task.kumparantestmbkm.data.model

import com.google.gson.annotations.SerializedName

data class AlbumResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
