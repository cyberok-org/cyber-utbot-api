package org.cyber.utbot.api.utils.additions

import soot.ArrayType
import soot.RefType
import soot.Scene
import soot.Type

internal val JAVA_NET_URI_TYPE: RefType
    get() = Scene.v().getSootClass(java.net.URI::class.java.canonicalName).type

internal val ELEMENT_ARRAY_TYPE: (Type) -> ArrayType
    get() = { Scene.v().getType(ArrayType.v(it, 1).toString()) as ArrayType }

internal val CHARSET_TYPE: RefType
    get() = Scene.v().getSootClass(java.nio.charset.Charset::class.java.canonicalName).type

