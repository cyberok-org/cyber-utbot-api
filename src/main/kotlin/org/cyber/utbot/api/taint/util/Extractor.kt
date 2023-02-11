package org.cyber.utbot.api.taint.util

import org.apache.commons.io.IOUtils
import java.nio.charset.Charset

fun extract(resource: String, clazz: Class<*>): String {
    val input = clazz.getResourceAsStream(resource)
    return IOUtils.toString(input, Charset.defaultCharset())
}
