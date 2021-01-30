package com.skilbox.a17_best_practices

import AddCar
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_for_add_car.*

class CarClassDialogFragment(private val carClass: String) : DialogFragment() {

    private val addCar: AddCar?
        get() = activity?.let { it as AddCar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_for_add_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("DialogFragment", "onActivityCreated")

        val classImage = dialog?.findViewById<ImageView>(R.id.classLogoView)
        val titleText = dialog?.findViewById<TextView>(R.id.titleTextView)
        val brend = dialog?.findViewById<EditText>(R.id.brendEditText)
        val carName = dialog?.findViewById<EditText>(R.id.carNameEditText)
        val carImage = dialog?.findViewById<EditText>(R.id.imageLincEditText)
        val engineCapacity = dialog?.findViewById<EditText>(R.id.engineCapacityEditText)
        val specialRow = dialog?.findViewById<EditText>(R.id.specialEditText)

        titleText?.text = "${titleText?.text} $carClass  "

        when (carClass) {
            "Класс A" -> {
                classImage?.setImageResource(R.drawable.class_a)
                specialRow?.inputType = InputType.TYPE_CLASS_TEXT
                specialRow?.hint = "Введите размер автомобиля"
            }
            "Класс B" -> {
                classImage?.setImageResource(R.drawable.class_b)
                specialRow?.hint = "Введите количество дверей"
            }
            "Класс C" -> {
                classImage?.setImageResource(R.drawable.class_c)
                specialRow?.hint = "Введите расход топлива"
            }
            "Класс D" -> {
                classImage?.setImageResource(R.drawable.class_d)
                specialRow?.hint = "Введите количество сидений"
            }
            "Класс E" -> {
                classImage?.setImageResource(R.drawable.class_e)
                specialRow?.visibility = INVISIBLE
            }
            "Класс J" -> {
                classImage?.setImageResource(R.drawable.class_j)
                specialRow?.hint = "Введите дорожный просвет"
            }

            else -> error("class: CarClassDialogFragment || fun: onActivityCreated carClass = $carClass not fund")
        }

        saveButton.setOnClickListener {
            Log.e("DialogFragment", "onCreateView")
            Log.e("saveButton", "if brent=${brend?.text} || carName=${carName?.text}")
            if (brend?.text?.length!! > 0 && carName?.text?.length!! > 0 && carImage?.text?.length!! > 0 && engineCapacity?.text?.length!! > 0) {

                Log.e("saveButton2", "if brent=${brend.text} || carName=${carName.text}")
                addCar?.addCar(
                    createCar(
                        id = addCar?.getMaxId(),
                        image_linc = carImage.text.toString(),
                        brend = brend.text.toString(),
                        name = carName.text.toString(),
                        engine_capacity = engineCapacity.text.toString(),
                        special = specialRow?.text.toString()
                    )
                )
            } else {
                Log.e("saveButton", "else")
                Toast.makeText(activity?.applicationContext, "Не все данные введены", Toast.LENGTH_SHORT).show()
            }

            dialog?.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog?.dismiss()
    }

    private fun createCar(
        id: Long?,
        image_linc: String,
        brend: String,
        name: String,
        engine_capacity: String,
        special: String?
    ): Cars {
        return when (carClass) {
            "Класс A" -> {
                Cars.A_class(
                    id!!, image_linc, brend, name, engine_capacity, special!!
                )
            }
            "Класс B" -> {
                Cars.B_class(
                    id!!, image_linc, brend, name, engine_capacity, special!!.toInt()
                )
            }
            "Класс C" -> {
                Cars.C_class(
                    id!!, image_linc, brend, name, engine_capacity, special!!.toInt()
                )
            }
            "Класс D" -> {
                Cars.D_class(
                    id!!, image_linc, brend, name, engine_capacity, special!!.toInt()
                )
            }
            "Класс E" -> {
                Cars.E_class(
                    id!!, image_linc, brend, name, engine_capacity
                )
            }
            "Класс J" -> {
                Cars.J_class(
                    id!!, image_linc, brend, name, engine_capacity, special!!.toInt()
                )
            }

            else -> error("class: CarClassDialogFragment || fun: onActivityCreated carClass = $carClass not fund")
        }
    }
}
