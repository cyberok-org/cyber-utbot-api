package org.cyber.utbot.api.utils.viewers

interface IViewable<T> {
    val viewers: MutableList<IViewer<T>>

    fun add(viewer: IViewer<T>) {
        viewers.add(viewer)
    }

    fun remove(viewer: IViewer<T>) {
        viewers.remove(viewer)
    }

    fun viewUpdate(t: T) {
        viewers.forEach { it.view(t) }
    }
}
