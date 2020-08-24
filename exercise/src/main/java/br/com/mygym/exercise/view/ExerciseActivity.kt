package br.com.mygym.exercise.view

import android.os.Bundle
import br.com.mygym.core.base.BaseActivity
import br.com.mygym.core.navcontroller.navProvider
import br.com.mygym.exercise.R
import br.com.mygym.exercise.databinding.ExerciseActivityMainBinding
import br.com.mygym.exercise.exerciseModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class ExerciseActivity : BaseActivity() {

    private lateinit var binding: ExerciseActivityMainBinding
    private val navController by navProvider(R.id.exerciseListFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(exerciseModule)
        binding = ExerciseActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.listFragment -> {
                finish()
            }

            else -> onSupportNavigateUp()
        }
    }

    override fun onDestroy() {
        unloadKoinModules(exerciseModule)
        super.onDestroy()
    }
}