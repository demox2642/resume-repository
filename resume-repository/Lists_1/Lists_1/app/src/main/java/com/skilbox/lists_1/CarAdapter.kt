package com.skilbox.lists_1

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarAdapter(
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var carsList: List<Cars> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_A -> CarClassAHolder(parent.inflate(R.layout.car_class_a), onItemClick)
            TYPE_B -> CarClassBHolder(parent.inflate(R.layout.car_class_b), onItemClick)
            TYPE_C -> CarClassCHolder(parent.inflate(R.layout.car_class_c), onItemClick)
            TYPE_D -> CarClassDHolder(parent.inflate(R.layout.car_class_d), onItemClick)
            TYPE_E -> CarClassEHolder(parent.inflate(R.layout.car_class_e), onItemClick)
            TYPE_J -> CarClassJHolder(parent.inflate(R.layout.car_class_j), onItemClick)
            else -> error("Нет такого вида Car")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CarClassAHolder -> {
                val car = carsList[position].let { it as? Cars.A_class }
                    ?: error("Car in position = $position is not Cars.A_class")
                holder.bind(car)
            }

            is CarClassBHolder -> {
                val car = carsList[position].let { it as? Cars.B_class }
                    ?: error("Car in position = $position is not Cars.B_class")
                holder.bind(car)
            }

            is CarClassCHolder -> {
                val car = carsList[position].let { it as? Cars.C_class }
                    ?: error("Car in position = $position is not Cars.C_class")
                holder.bind(car)
            }

            is CarClassDHolder -> {
                val car = carsList[position].let { it as? Cars.D_class }
                    ?: error("Car in position = $position is not Cars.D_class")
                holder.bind(car)
            }

            is CarClassEHolder -> {
                val car = carsList[position].let { it as? Cars.E_class }
                    ?: error("Car in position = $position is not Cars.E_class")
                holder.bind(car)
            }

            is CarClassJHolder -> {
                val car = carsList[position].let { it as? Cars.J_class }
                    ?: error("Car in position = $position is not Cars.J_class")
                holder.bind(car)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (carsList[position]) {
            is Cars.A_class -> TYPE_A
            is Cars.B_class -> TYPE_B
            is Cars.C_class -> TYPE_C
            is Cars.D_class -> TYPE_D
            is Cars.E_class -> TYPE_E
            is Cars.J_class -> TYPE_J
        }
    }

    override fun getItemCount(): Int = carsList.size

    fun updateCarList(newCars: List<Cars>) { carsList = newCars }

    abstract class BaseCarHolder(

        view: View,
        private val onItemClick: (position: Int) -> Unit

    ) : RecyclerView.ViewHolder(view) {

        private val imageLincView: ImageView = view.findViewById(R.id.car_imege)
        private val brendTextView: TextView = view.findViewById(R.id.car_brend)
        private val nameTextView: TextView = view.findViewById(R.id.car_name)
        private val engineCapacityTextView: TextView = view.findViewById(R.id.engine_capacity_textview)

        init {
            Log.e("BaseCarHolder1", "init")
            view.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        protected fun bindMainInfo(
            image_linc: String,
            brend: String,
            name: String,
            engine_capacity: String
        ) {
            Log.e("BaseCarHolder2", "init")

            brendTextView.text = brend
            nameTextView.text = name
            engineCapacityTextView.text = "Объем двигателя : $engine_capacity"

            Log.e("BaseCarHolder3", "bind + $brend")

            Glide.with(itemView)
                .load(image_linc)
                .placeholder(R.drawable.load_imege)
                .into(imageLincView)
        }
    }

    class CarClassAHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        private val sizeTextView: TextView = view.findViewById(R.id.sizeTextView)

        fun bind(cars: Cars.A_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
            sizeTextView.text = "Размеры : " + cars.size
        }
    }

    class CarClassBHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        private val dorsQuantityTextView: TextView = view.findViewById(R.id.dorsTextView)

        fun bind(cars: Cars.B_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
            dorsQuantityTextView.text = "Количество дверей : " + cars.doors_quantity
        }
    }

    class CarClassCHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        private val fuelConsumptionTextView: TextView = view.findViewById(R.id.fuel_consumption_TextView)

        fun bind(cars: Cars.C_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
            fuelConsumptionTextView.text = "Расход топлива: " + cars.fuel_consumption
        }
    }

    class CarClassDHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        private val seatQuantityTextView: TextView = view.findViewById(R.id.seat_quantity_TextView)

        fun bind(cars: Cars.D_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
            seatQuantityTextView.text = "Количество мест : " + cars.seat_quantity
        }
    }

    class CarClassEHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        fun bind(cars: Cars.E_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
        }
    }

    class CarClassJHolder(
        view: View,
        onItemClick: (position: Int) -> Unit
    ) : BaseCarHolder(view, onItemClick) {

        private val liftSizeTextView: TextView = view.findViewById(R.id.lift_size_TextView)

        fun bind(cars: Cars.J_class) {
            bindMainInfo(brend = cars.brend, name = cars.name, engine_capacity = cars.engine_capacity, image_linc = cars.image_linc)
            liftSizeTextView.text = "Дорожный просвет : " + cars.lift_size
        }
    }

    companion object {
        private const val TYPE_A = 1
        private const val TYPE_B = 2
        private const val TYPE_C = 3
        private const val TYPE_D = 4
        private const val TYPE_E = 5
        private const val TYPE_J = 6
    }
}
