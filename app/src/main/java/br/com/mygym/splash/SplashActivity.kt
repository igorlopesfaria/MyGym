package br.com.mygym.splash

import android.os.Bundle
import br.com.mygym.core.base.BaseActivity
import br.com.mygym.R
import br.com.mygym.core.util.navigateTo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        activityScope.launch(Dispatchers.IO) {
            delay(3000)
            openExerciseModule()
        }

    }

    private fun openExerciseModule() {
        this.navigateTo(getString(R.string.exercise_path))
        finish()
    }

}