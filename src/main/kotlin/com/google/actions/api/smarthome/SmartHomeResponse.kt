/*
 * Copyright 2018 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.actions.api.smarthome

import com.google.home.graph.v1.DeviceProto
import org.json.JSONObject

open class SmartHomeResponse {
    open fun build(): JSONObject {
        return JSONObject() // Return empty object
    }
}

class SyncResponse : SmartHomeResponse() {
    lateinit var requestId: String
    lateinit var payload: Payload

    override fun build(): JSONObject {
        val json = JSONObject()
        json.put("requestId", requestId)
        json.put("payload", payload.build())
        return json
    }

    class Payload {
        lateinit var agentUserId: String
        lateinit var devices: Array<DeviceProto.Device>

        fun build(): JSONObject {
            val json = JSONObject()
            json.put("agentUserId", agentUserId)
            json.put("devices", devices)
            return json
        }
    }
}

class QueryResponse : SmartHomeResponse() {
    lateinit var requestId: String
    lateinit var payload: Payload

    override fun build(): JSONObject {
        val json = JSONObject()
        json.put("requestId", requestId)
        json.put("payload", payload.build())
        return json
    }

    class Payload {
        lateinit var devices: Map<String, Map<String, kotlin.Any>>

        fun build(): JSONObject {
            val json = JSONObject()
            json.put("devices", devices)
            return json
        }
    }
}

class ExecuteResponse : SmartHomeResponse() {
    lateinit var requestId: String
    lateinit var payload: Payload

    override fun build(): JSONObject {
        val json = JSONObject()
        json.put("requestId", requestId)
        json.put("payload", payload.build())
        return json
    }

    class Payload {
        lateinit var commands: Array<Commands>

        fun build(): JSONObject {
            val json = JSONObject()
            json.put("commands", commands.map { command -> command.build() })
            return json
        }

        class Commands {
            lateinit var ids: Array<String>
            lateinit var status: String

            var states: Map<String, kotlin.Any>? = null
            var errorCode: String? = null

            fun build(): JSONObject {
                val json = JSONObject()
                json.put("ids", ids)
                json.put("status", status)
                json.put("states", states)
                json.put("errorCode", errorCode)
                return json
            }
        }
    }
}