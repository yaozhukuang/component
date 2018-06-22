package com.zw.yzk.component.common.utils

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter


/**
 *
 * 使用gson反序列化时自动将String对应null替换为“”
 */

class StringTypeAdapter : TypeAdapter<String>() {
    override fun write(write: JsonWriter?, value: String?) {
        if (value == null) {
            write?.nullValue()
        }
        write?.value(value)
    }

    override fun read(reader: JsonReader?): String {
        if (reader?.peek() == JsonToken.NULL) {
            reader.nextNull()
            return ""
        }
        return reader?.nextString() ?: ""
    }
}