package org.cyber.utbot.api.utils

import java.io.File
import java.nio.file.Paths

const val VULNERABILITY_CHECKS_CLASS_NAME = "org.cyber.utils.VulnerabilityChecks"
const val VULNERABILITY_CHECKS_FUNCTIONS_CLASS_NAME = "org.cyber.utils.VulnerabilityChecksFunctions"
const val CHECK_METHOD_PREFIX = "internalCheck"
const val ASSERT_CLASS_NAME = "org.cyber.utils.Utils"
const val ASSERT_FUNCTION_NAME = "vulnerabilityAssertByMsg"
private const val GENERATE_METHOD_PREFIX = "internalGenerate"
const val GENERATE_METHOD_ARGUMENTS_PREFIX = "${GENERATE_METHOD_PREFIX}Arguments"
val UTBOT_DIR = Paths.get("").toAbsolutePath().toString().dropLastWhile { it != File.separatorChar }.dropLast(1)
const val DEFAULT_BUILS_CLASSES_PATH = "build/classes/java/main"
