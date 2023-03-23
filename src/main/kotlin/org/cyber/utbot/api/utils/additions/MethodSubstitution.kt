package org.cyber.utbot.api.utils.additions
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.types.STRING_TYPE
import org.utbot.framework.util.graph
import soot.Scene
import soot.SootClass
import soot.SootMethod


object MethodSubstitution {
    private fun methodResult(method: SootMethod?) = method?.run { listOf(GraphResult(jimpleBody().graph())) }

    // path
    private val pathClass: SootClass
        get() = Scene.v().getSootClass(java.nio.file.Path::class.qualifiedName)
    private val pathOf1Signature = pathClass.getMethod("of", listOf(JAVA_NET_URI_TYPE)).signature
    private val pathOf2Signature = pathClass.getMethod("of", listOf(STRING_TYPE, ELEMENT_ARRAY_TYPE(STRING_TYPE))).signature
    private val cyberPathClass: SootClass
        get() = Scene.v().getSootClass(org.cyber.utils.overrides.CyberPath::class.qualifiedName)
    private val cyberPathOf1 = cyberPathClass.getMethod("of", listOf(JAVA_NET_URI_TYPE))
    private val cyberPathOf2 = cyberPathClass.getMethod("of", listOf(STRING_TYPE, ELEMENT_ARRAY_TYPE(STRING_TYPE)))

    // files
    private val filesClass: SootClass
        get() = Scene.v().getSootClass(java.nio.file.Files::class.qualifiedName)
    private val filesCreateFileSignature = filesClass.getMethodByName(java.nio.file.Files::createFile.name).signature
    private val filesCreateDirectoriesSignature = filesClass.getMethodByName(java.nio.file.Files::createDirectories.name).signature

    // URLDecoder
//    private val URLDecoderClass: SootClass
//        get() = Scene.v().getSootClass(java.net.URLDecoder::class.qualifiedName)
//    private val URLDecoderClassDecode1Signature = URLDecoderClass.getMethod("decode", listOf(STRING_TYPE, STRING_TYPE)).signature
//    private val URLDecoderClassDecode2Signature = URLDecoderClass.getMethod("decode", listOf(STRING_TYPE)).signature
//    private val URLDecoderClassDecode3Signature = URLDecoderClass.getMethod("decode", listOf(STRING_TYPE, CHARSET_TYPE)).signature

    //

    //

    private val overrideMethods = mapOf<String, CyberTraverser.(wrapper: ObjectValue?, List<SymbolicValue>) -> List<InvokeResult>?>(
        pathOf1Signature to { _, _ -> methodResult(cyberPathOf1) },
        pathOf2Signature to { _, _ -> methodResult(cyberPathOf2) },
//        filesCreateFileSignature to { _, parameters -> listOf(MethodResult(parameters[0])) },
//        filesCreateDirectoriesSignature to { _, parameters -> listOf(MethodResult(parameters[0])) },
//        URLDecoderClassDecode1Signature to { _, parameters -> listOf(MethodResult(parameters[0])) },
//        URLDecoderClassDecode2Signature to { _, parameters -> listOf(MethodResult(parameters[0])) },
//        URLDecoderClassDecode3Signature to { _, parameters -> listOf(MethodResult(parameters[0])) },
    )

    fun CyberTraverser.overrideInvoke(
        wrapper: ObjectValue?,
        method: SootMethod,
        parameters: List<SymbolicValue>
    ): List<InvokeResult>? = overrideMethods[method.signature]?.run { this(wrapper, parameters) }
}
