package org.cyber.utbot.api.utils.annotations

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class CyberNotModify(val ref: String, val reason: String = "")


@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class CyberModify(val ref: String, val change: String = "")


@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION, AnnotationTarget.LOCAL_VARIABLE)
@Retention(AnnotationRetention.SOURCE)
annotation class CyberNew(val reason: String = "")


@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.EXPRESSION, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class CyberNote(val what: String = "")
