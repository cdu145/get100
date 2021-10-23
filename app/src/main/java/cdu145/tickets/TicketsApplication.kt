package cdu145.tickets

import android.app.Application
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import cdu145.tickets.digits.TicketDigitsModule
import cdu145.tickets.guide.GuideModule
import cdu145.tickets.hint.HintModule
import cdu145.tickets.hint.available.ReviseAvailableHints
import cdu145.tickets.number.EnsureTicketNumberCreated
import cdu145.tickets.number.TicketNumberModule
import cdu145.tickets.ratedialog.RateDialogModule
import cdu145.tickets.solution.SolutionModule
import cdu145.tickets.solution.clear.ClearSolutionModule
import cdu145.tickets.solution.correct.CorrectSolutionsModule
import cdu145.tickets.solution.gaps.SolutionGapsModule
import cdu145.tickets.solution.result.SolutionResultModule
import cdu145.tickets.solution.signs.SignButtonsModule
import cdu145.tickets.ticket.TicketModule
import cdu145.tickets.vibration.VibrationModule

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