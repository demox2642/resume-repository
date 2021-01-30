package com.skilbox.lists_1

sealed class Cars {
    data class A_class(
        val image_linc: String,
        val brend: String,
        val name: String,
        val engine_capacity: String,
        val size: String
    ) : Cars()

    data class B_class(
        val image_linc: String,
        val brend: String,
        val name: String,
        val engine_capacity: String,
        val doors_quantity: Int
    ) : Cars()

    data class C_class(
        val image_linc: String,
        val brend: String,
        val name: String,
        val engine_capacity: String,
        val fuel_consumption: Int
    ) : Cars()

    data class D_class(
        val image_linc: String,
        val brend: String,
        val name: String,
        val engine_capacity: String,
        val seat_quantity: Int
    ) : Cars()

    data class E_class(
        val image_linc: String,
        val brend: String,
        val name: String,
        val engine_capacity: String
    ) : Cars()

    data class J_class(
        val image_linc: String,
        val brend: String,
        val name: String,
        val engine_capacity: String,
        val lift_size: Int
    ) : Cars()
}
