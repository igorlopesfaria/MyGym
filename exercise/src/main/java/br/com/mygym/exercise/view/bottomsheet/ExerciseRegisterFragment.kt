package br.com.mygym.exercise.view.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mygym.exercise.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ExerciseRegisterFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG: String = "ExerciseRegisterFragment"

        fun newInstance(): ExerciseRegisterFragment {
            return ExerciseRegisterFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_register, container, false)
    }
}