package org.cyber.utbot.api.utils.annotations

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class CyberNotModify(val ref: String, val reason: String = "")


@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class CyberModify(val ref: String, val change: String = "")


@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
annotation class CyberNew(val reason: String = "")
