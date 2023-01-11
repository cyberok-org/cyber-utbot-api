package org.cyber.utbot.api.abstraction

import kotlinx.serialization.Serializable

@Serializable
class JsonStatistic(val path: List<String>, val assertions: List<String>, val assumption: List<String>, val lastStatus: String)
