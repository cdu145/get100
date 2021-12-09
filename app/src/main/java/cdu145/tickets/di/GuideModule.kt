package cdu145.tickets.di

import android.content.Context
import cdu145.model.Actual
import cdu145.model.DataStoreActual
import cdu145.model.DialogState.Hidden
import cdu145.tickets.data.guideCompletedFlagDataStore
import cdu145.tickets.model.guide.Guide
import cdu145.tickets.viewmodel.GuideDialogViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val GuideDialogStateFlow = StringQualifier("GuideDialogState")
val GuideCompletedFlag = StringQualifier("GuideCompletedFlag")

val Pages = StringQualifier("Pages")
val PageCount = StringQualifier("PageCount")

val GuideModule = module {
    scope<Guide> {
        scoped(Pages) { Guide.pages() }
        scoped(PageCount) { get<List<*>>(Pages).size }
    }

    factory<Actual.Mutable<Boolean>>(GuideCompletedFlag) {
        DataStoreActual(get<Context>().guideCompletedFlagDataStore)
    } bind Actual::class

    single(GuideDialogStateFlow) { MutableStateFlow(Hidden) }

    viewModel {
        GuideDialogViewModel(
            get(GuideDialogStateFlow),
            get(GuideCompletedFlag),
        )
    }
}