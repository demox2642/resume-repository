package com.skilbox.viewmodelandnav.SGLM

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.skilbox.viewmodelandnav.R
import kotlinx.android.synthetic.main.fragment_car_class_dialog.*

class CarClassDialogFragmentSGLM : DialogFragment() {
    private val args: CarClassDialogFragmentSGLMArgs by navArgs()

    private val carListViewModel: CarListViewModelSGLM by navGraphViewModels(R.id.SGLM) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_car_class_dialog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("DialogFragment", "onActivityCreated")

        titleTextView.text = "${titleTextView.text}  ${args.carClass}"

        when (args.carClass) {
            "Класс A" -> {
                classLogoView.setImageResource(R.drawable.class_a)
                specialEditText.hint = "Введите размер автомобиля"
            }
            "Класс B" -> {
                classLogoView.setImageResource(R.drawable.class_b)
                specialEditText.hint = "Введите количество дверей"
            }
            "Класс C" -> {
                classLogoView.setImageResource(R.drawable.class_c)
                specialEditText.hint = "Введите расход топлива"
            }
            "Класс D" -> {
                classLogoView.setImageResource(R.drawable.class_d)
                specialEditText.hint = "Введите количество сидений"
            }
            "Класс E" -> {
                classLogoView.setImageResource(R.drawable.class_e)
                specialEditText.visibility = View.INVISIBLE
            }
            "Класс J" -> {
                classLogoView.setImageResource(R.drawable.class_j)
                specialEditText.hint = "Введите дорожный просвет"
            }

            else -> error("class: CarClassDialogFragment || fun: onActivityCreated carClass = ${args.carClass} not fund")
        }

        saveButton.setOnClickListener {
            Log.e("DialogFragment", "onCreateView")
            view?.clearFocus()
            if (brendEditText.text?.length!! > 0 && carNameEditText.text?.length!! > 0 && imageLincEditText.text?.length!! > 0 // &&engine_capacity_textview.text?.length!! > 0
            ) {
                carListViewModel.addCar(
                    newCarClass = args.carClass,
                    newCarImageLinc = imageLincEditText.text.toString(),
                    newCarBrend = brendEditText.text.toString(),
                    newCarName = carNameEditText.text.toString(),
                    newCarEngineCapacity = engineCapacityEditText.text.toString(),
                    newCarSpecial = specialEditText.text.toString()

                )

                findNavController().popBackStack()
            } else {

                Toast.makeText(
                    activity?.applicationContext,
                    "Не все данные введены",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }
}
