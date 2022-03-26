package com.omsuperg.msranking.model

import com.google.gson.annotations.SerializedName

data class ServerResponse (
    @SerializedName("CharacterData") val characterData : CharacterData
)