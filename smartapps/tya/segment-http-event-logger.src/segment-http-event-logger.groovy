/**
 * Segment Http Event Logger
 *
 * Copyright 2022 Ty Alexander
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 * for the specific language governing permissions and limitations under the License.
 *
 */
definition(
    name: "Segment HTTP Event Logger",
    namespace: "tya",
    author: "Ty Alexander",
    description: "Send Samsung SmartThing events to Segment HTTP Event Tracking API",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")

preferences {
  section ("Segment Tracking API Write Key") {
    input "segmentWriteKey", "text", title: "Segment Write Key", required: true, title "Segment Http Write-Key"
  }
  section("Log these switches:") {
    input "switches", "capability.switch", multiple: true, required: false, title: "Forward it Segment for switches"
  }
}

def installed() {
  initialize()
}

def updated() {
  unsubscribe()
  initialize()
}

def initialize() {
  subscribe(switches,	"switch", segmentHttpEventLogger)
}

def segmentHttpEventLogger(evt) {
  log.debug evt
}

    // def token = segmentWriteKey.bytes.endoceBase64().toString()
    // def params = [
    //     uri: "https://api.segment.io",
    //     path: "/v1/track",
    //     headers: ["Authorization": "basic ${token}"],
    //     body: ["event": evt],
    // ]

    // log.debug("Sending event to Segment")
    // log.debug(params)
    // try {
    //     httpPostJson(params) { resp ->
    //         log.debug "response message ${resp}"
    //     }
    // } catch (e) {
    //     // successful creates come back as 200, so filter for 'Created' and throw anything else
    //     if (e.toString() != 'groovyx.net.http.ResponseParseException: Created') {
    //         log.error "Error sending event: $e"
    //         throw e
    //     }
    // }
