package cdu145.tickets.di

import android.content.Context
import androidx.core.content.getSystemService
import cdu145.tickets.model.OneShotVibration
import org.koin.dsl.module

val VibrationModule = module {
    factory {
        OneShotVibration(
            vibrator = get<Context>().getSystemService()!!,
        )
    }
}