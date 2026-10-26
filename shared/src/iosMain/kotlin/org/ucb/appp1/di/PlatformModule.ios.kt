package org.ucb.appp1.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.ucb.appp1.tareas.domain.service.NotificationService

actual fun platformModule(): Module = module {
    single { NotificationService() }
}
