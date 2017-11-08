package com.johnny.gank.core.http

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer(vararg patterns: String) : JsonDeserializer<Date> {

    private var mDateFormatList: MutableList<SimpleDateFormat> = arrayListOf()

    init {
        for (pattern in patterns) {
            mDateFormatList.add(SimpleDateFormat(pattern, Locale.getDefault()))
        }
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date?{
        for (dateFormat in mDateFormatList) {
            try {
                return dateFormat.parse(json.asString)
            }
            catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return null
    }
}
