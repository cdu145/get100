package cdu145.model

enum class DialogState {

    Shown, Hidden;

    companion object {

        fun shownIf(condition: Boolean): DialogState = if (condition) Shown else Hidden
    }
}