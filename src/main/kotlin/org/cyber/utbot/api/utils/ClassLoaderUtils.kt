package org.cyber.utbot.api.utils

import org.utbot.framework.util.classesToLoad
import java.io.File
import kotlin.reflect.KClass

private val CYBER_UTILS_CLASSES = listOf(
    org.cyber.utils.overrides.CyberArray::class,
    org.cyber.utils.overrides.CyberEnumeration::class,
    org.cyber.utils.overrides.CyberPath::class,
    org.cyber.utils.Utils::class,
    org.cyber.utils.VulnerabilityChecks::class,
    org.cyber.utils.VulnerabilityChecksFunctions::class,
    org.cyber.utils.VulnerabilityException::class,
    org.cyber.utils.VulnerabilityInfo::class,
)

internal fun loadClassesFromPath(classLoader: ClassLoader, pathToClassPathRoot: String): List<KClass<*>> {
    val classFiles = mutableListOf<KClass<*>>()
    // Create a java file from the path
    val root = File(pathToClassPathRoot)
    if (!root.exists()) {
        return emptyList()
    }
    //Scan and find all class files
    root.walkTopDown().forEach { file ->
        if (file.extension == "class") {
            // construct relative path
            val relativePathForClass = file.normalize().relativeTo(root)
            val fqnClassName = relativePathForClass.parent.replace('/', '.')
                .replace('\\', '.') + '.' + relativePathForClass.nameWithoutExtension
            classFiles.add(classLoader.loadClass(fqnClassName).kotlin)
        }
    }
    return classFiles
}

internal fun updateClassesToLoad() {
    classesToLoad += CYBER_UTILS_CLASSES.map { it.java }.toTypedArray()
}
