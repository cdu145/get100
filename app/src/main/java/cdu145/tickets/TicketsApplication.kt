package cdu145.tickets

import android.app.Application
import cdu145.tickets.di.*
import cdu145.tickets.model.hint.ReviseAvailableHints
import cdu145.tickets.model.ticketnumber.EnsureTicketNumberCreated
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TicketsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TicketsApplication)
            modules(
                TicketNumberModule + TicketDigitsModule + GuideModule
                    + SolutionGapsModule + SignButtonsModule + SolutionResultModule
                    + ClearSolutionModule + SolutionModule + CorrectSolutionsModule
                    + HintModule + ApplicationModule + TicketModule + VibrationModule
                    + RateDialogModule
            )
        }

        getKoin().run {
            get<EnsureTicketNumberCreated>().invoke()
            get<ReviseAvailableHints>().invoke()
        }
    }
}