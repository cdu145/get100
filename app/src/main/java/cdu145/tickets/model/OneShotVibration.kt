package cdu145.tickets.model

import android.os.Build
import android.os.VibrationEffect
import android.os.VibrationEffect.createOneShot
import android.os.Vibrator

class OneShotVibration(
    private val vibrator: Vibrator,
) {

    fun start(duration: Long) {
        with (vibrator) {
            if (!hasVibrator()) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrate(
                    createOneShot(
                        duration,
                        VibrationEffect.DEFAULT_AMPLITUDE,
                    ),
                )
            } else {
                @Suppress("DEPRECATION")
                vibrate(duration)
            }
        }
    }
}