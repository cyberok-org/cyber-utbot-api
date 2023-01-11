package org.cyber.utbot.api.utils.viewers

import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.viewers.stateViewers.AnyStateViewer
import org.cyber.utbot.api.utils.viewers.stateViewers.EndNotTerminalStatisticViewer
import org.cyber.utbot.api.utils.viewers.stateViewers.TerminalStatisticViewer

enum class UTBotViewers {
    TERMINAL_STATISTIC_VIEWER,
    END_NOT_TERMINAL_STATISTIC_VIEWER;

    fun stateViewer(): AnyStateViewer? = when(this) {
        TERMINAL_STATISTIC_VIEWER -> TerminalStatisticViewer()
        END_NOT_TERMINAL_STATISTIC_VIEWER -> EndNotTerminalStatisticViewer()
        else -> null
    }
}

fun <T> IViewer<T>.utbotViewer() = when(this) {
    is TerminalStatisticViewer -> UTBotViewers.TERMINAL_STATISTIC_VIEWER
    is EndNotTerminalStatisticViewer -> UTBotViewers.END_NOT_TERMINAL_STATISTIC_VIEWER
    else -> throw CyberException("wrong IViewer")
}
