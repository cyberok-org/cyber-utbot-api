package org.cyber.utbot.api.abstraction

import kotlinx.serialization.Serializable

@Serializable
class JsonStatistics(val data: List<JsonStatistic>)
