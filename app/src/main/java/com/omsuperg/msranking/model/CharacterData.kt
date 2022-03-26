package com.omsuperg.msranking.model

import com.google.gson.annotations.SerializedName

data class CharacterData(

    @SerializedName("AchievementPoints") val achievementPoints: Int,
    @SerializedName("AchievementRank") val achievementRank: Int,
    @SerializedName("CharacterImageURL") val characterImageURL: String,
    @SerializedName("Class") val clazz: String,
    @SerializedName("ClassRank") val classRank: Int,
    @SerializedName("EXP") val eXP: Long,
    @SerializedName("EXPPercent") val eXPPercent: Double,
    @SerializedName("GlobalRanking") val globalRanking: Int,
    @SerializedName("Guild") val guild: String,
    @SerializedName("LegionLevel") val legionLevel: Int,
    @SerializedName("LegionPower") val legionPower: Int,
    @SerializedName("LegionRank") val legionRank: Int,
    @SerializedName("Level") val level: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("Server") val server: String,
    @SerializedName("ServerClassRanking") val serverClassRanking: Int,
    @SerializedName("ServerRank") val serverRank: Int,
    @SerializedName("ServerSlug") val serverSlug: String
)

fun CharacterData?.levelWithPercent(): String {
    this?.let {
        return "Level ${this.level} (${this.eXPPercent}%) | ${this.clazz} | ${this.server}"
    }
    return ""
}

fun CharacterData?.ranks(): String {
    this?.let {
        return "Global Rank: ${this.globalRanking}\n${this.server} Rank: ${this.serverRank}\n${this.clazz} Rank: ${this.classRank}"
    }
    return ""
}
