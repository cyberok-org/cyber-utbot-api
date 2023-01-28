package org.cyber.utbot.api.taint

import proguard.classfile.ClassPool
import proguard.classfile.visitor.ClassNameFilter
import proguard.classfile.visitor.ClassPoolFiller
import proguard.io.*
import proguard.util.ExtensionMatcher
import proguard.util.OrMatcher
import java.io.File
import java.io.IOException


/**
 * This utility class provides methods to read and write the classes in jars.
 */
object JarUtil {
    /**
     * Reads the classes from the specified jar file and returns them as a class
     * pool.
     *
     * @param jarFileName the name of the jar file or jmod file.
     * @param isLibrary   specifies whether classes should be represented as
     * ProgramClass instances (for processing) or
     * LibraryClass instances (more compact).
     * @return a new class pool with the read classes.
     */
    @Throws(IOException::class)
    fun readJar(
        jarFileName: String?,
        classNameFilter: String?,
        isLibrary: Boolean
    ): ClassPool {
        val classPool = ClassPool()

        // Parse all classes from the input jar and
        // collect them in the class pool.
        val source: DataEntrySource = FileSource(
            File(jarFileName)
        )
        val classPoolFiller = ClassPoolFiller(classPool)
        var classReader: DataEntryReader = NameFilteredDataEntryReader(
            "**.class",
            ClassReader(
                isLibrary, false, false, false, null,
                ClassNameFilter(
                    classNameFilter,
                    classPoolFiller
                )
            )
        )

        // Extract files from an archive if necessary.
        classReader = FilteredDataEntryReader(
            DataEntryNameFilter(ExtensionMatcher("aar")),
            JarReader(
                NameFilteredDataEntryReader(
                    "classes.jar",
                    JarReader(classReader)
                )
            ),
            FilteredDataEntryReader(
                DataEntryNameFilter(
                    OrMatcher(
                        ExtensionMatcher("jar"),
                        ExtensionMatcher("zip"),
                        ExtensionMatcher("apk")
                    )
                ),
                JarReader(classReader),
                classReader
            )
        )
        source.pumpDataEntries(classReader)
        return classPool
    }
}