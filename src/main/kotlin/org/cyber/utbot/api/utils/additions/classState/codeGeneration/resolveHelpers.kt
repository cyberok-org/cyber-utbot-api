package org.cyber.utbot.api.utils.additions.classState.codeGeneration

internal fun <V, M> MutableMap<V, V>.resolve(resolve: (V) -> M): MutableMap<M, M> = map { resolve(it.key) to resolve(it.value) }.toMap().toMutableMap()

internal fun <V, M> MutableMap<String, V>.resolveConcreteKey(resolve: (V) -> M): MutableMap<String, M> = map { it.key to resolve(it.value) }.toMap().toMutableMap()
