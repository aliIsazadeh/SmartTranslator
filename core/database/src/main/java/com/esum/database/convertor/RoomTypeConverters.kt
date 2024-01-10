package com.esum.database.convertor

import androidx.room.TypeConverter
import com.esum.database.entity.model.Antonym
import com.esum.database.entity.model.Synonym
import com.google.gson.Gson

class RoomTypeConverters {

    @TypeConverter
    fun convertSynonymListToJSONString(synonym: Synonym): String = Gson().toJson(synonym)
    @TypeConverter
    fun convertJSONStringToSynonymList(jsonString: String): Synonym =
        Gson().fromJson(jsonString, Synonym::class.java)
    @TypeConverter
    fun convertAntonymListToJSONString(antonym: Antonym): String = Gson().toJson(antonym)
    @TypeConverter
    fun convertJSONStringToAntonymList(jsonString: String): Antonym =
        Gson().fromJson(jsonString, Antonym::class.java)

}