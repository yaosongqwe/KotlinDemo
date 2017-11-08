package com.ysdemo.mykotlin.util

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * JustDemo
 * Created by Song on 2017/9/22.
 */
class JsonTestUtil {

    fun upCaseKey(json: JSONObject): JSONObject {
        var key: String?
        val keys = ArrayList<String>()
        json.keys().forEach {
            keys.add(it as String)
        }
        keys.forEach {
            if (Character.isLowerCase(it[0])) {
                key = it
                val value = json.get(key)
                json.remove(key)
                key = upCaseKeyFirstChar(key)
                json.put(key, value)
            }
        }
        json.keys().forEach {
            val value = json.get(it as String)
            when (value) {
                is JSONArray -> {
                    if (value.length() > 0) {
                        for (i in 0..value.length() - 1) {
                                upCaseKey(value[i] as JSONObject)
                        }
                    }
                }
                is JSONObject -> {
                    upCaseKey(value)
                }
            }
        }
        return json
    }

    private fun upCaseKeyFirstChar(key: String?): String? {
        return if (Character.isUpperCase(key!![0])) {
            key
        } else {
            StringBuilder().append(Character.toUpperCase(key[0])).append(key.substring(1)).toString()
        }
    }

    fun testUpCaseKey(): Boolean {
        try {
            var json = JSONObject("{" +
                    "    \"test1\": \"1\"," +
                    "    \"testList\": [" +
                    "        {\n" +
                    "            \"test2\": 2," +
                    "            \"test3\": \"3\"" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"testObj\": {" +
                    "        \"test4\": \"4\"," +
                    "        \"test5\": \"5\"," +
                    "        " +
                    "    }" +
                    "}")
            print("\ntestUpCaseKey input:" + json.toString())
            val time1 = System.currentTimeMillis()
//            print("testUpCaseKey time1:" + time1)
            json = JsonTestUtil().upCaseKey(json)
            val time2 = System.currentTimeMillis()
//            print("testUpCaseKey time2:" + time2)
            print("\ntestUpCaseKey result:" + json.toString())
            print("\ntestUpCaseKey time:" + (time2 - time1))
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }
    }
}