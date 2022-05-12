package com.example.sensible.util

import com.google.gson.*
import java.lang.reflect.Type
import java.math.BigDecimal


private class NaturalDeserializer : JsonDeserializer<Any?> {
    override fun deserialize(
        json: JsonElement, typeOfT: Type?,
        context: JsonDeserializationContext
    ): Any? {
        return if (json.isJsonNull) null else if (json.isJsonPrimitive) handlePrimitive(json.asJsonPrimitive) else if (json.isJsonArray()) handleArray(
            json.asJsonArray,
            context
        ) else handleObject(json.asJsonObject, context)
    }

    private fun handlePrimitive(json: JsonPrimitive): Any {
        return json.asString
    }

    private fun handleArray(json: JsonArray, context: JsonDeserializationContext): Any {
        val array = arrayOfNulls<Any>(json.size())
        for (i in array.indices) array[i] = context.deserialize(json.get(i), Any::class.java)
        return array
    }

    private fun handleObject(json: JsonObject, context: JsonDeserializationContext): Any {
        val map: MutableMap<String, Any> = HashMap()
        for ((key, value) in json.entrySet()) map[key] =
            context.deserialize(
                value,
                Any::class.java
            )
        return map
    }
}
