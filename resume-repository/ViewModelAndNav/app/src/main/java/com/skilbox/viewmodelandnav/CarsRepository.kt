package com.skilbox.viewmodelandnav

import kotlin.random.Random

class CarsRepository {

    private var carsList = listOf(
        Cars.A_class(
            1,
            "https://auto.ironhorse.ru/wp-content/uploads/2019/09/i10-3-NLine-550x343.jpg",
            "Hyundai",
            "i10",
            "1.0",
            "1480 x 1680"
        ),
        Cars.A_class(
            2,
            "https://auto.ironhorse.ru/wp-content/uploads/2018/03/picanto-3-X-550x343.jpg",
            "Kia",
            "Picanto",
            "1.2",
            "1495 x 1595"
        ),
        Cars.A_class(
            3,
            "https://auto.ironhorse.ru/wp-content/uploads/2017/12/ForFour-crosstown-550x412.jpg",
            "Smart",
            "ForFour",
            "0.9",
            "1665 x 1554"
        ),
        Cars.B_class(
            4,
            "https://auto.ironhorse.ru/wp-content/uploads/2017/06/polo-6-550x309.jpg",
            "Volkswagen",
            "Polo",
            "1.5",
            5
        ),
        Cars.B_class(
            5,
            "https://auto.ironhorse.ru/wp-content/uploads/2020/03/rapid-2-front-550x343.jpg",
            "Skoda",
            "Rapid",
            "1.4",
            4
        ),
        Cars.B_class(
            6,
            "https://auto.ironhorse.ru/wp-content/uploads/2017/02/Focus-ST-3-550x343.jpg",
            "Ford",
            "Fiesta",
            "1.5",
            3
        ),
        Cars.C_class(
            7,
            "https://auto.ironhorse.ru/wp-content/uploads/2020/02/leon-4-hatch-face1-550x343.jpg",
            "Seat",
            "Leon",
            "2.0",
            5
        ),
        Cars.C_class(
            8,
            "https://auto.ironhorse.ru/wp-content/uploads/2018/05/Insight-3-550x309.jpg",
            "Honda",
            "Insight",
            "1.5",
            6
        ),
        Cars.C_class(
            9,
            "https://auto.ironhorse.ru/wp-content/uploads/2013/06/q301-550x309.jpg",
            "Infinity",
            "Q30",
            "2.0",
            9
        ),
        Cars.D_class(
            10,
            "https://auto.ironhorse.ru/wp-content/uploads/2011/12/Zafira-C-Tourer-550x309.jpg",
            "Opel",
            "Zafira",
            "2.0",
            7
        ),
        Cars.D_class(
            11,
            "https://auto.ironhorse.ru/wp-content/uploads/2014/02/BMW-2-Series-Active-Tourer-550x309.jpg",
            "BMW",
            "2 Tourer",
            "2.0",
            4
        ),
        Cars.D_class(
            12,
            "https://auto.ironhorse.ru/wp-content/uploads/2018/10/V60-Cross-Country-550x343.jpg",
            "Volvo",
            "V60",
            "2.5",
            5
        ),
        Cars.E_class(
            13,
            "https://auto.ironhorse.ru/wp-content/uploads/2018/04/es7-550x309.jpg",
            "Lexus",
            "ES",
            "3.5"

        ),
        Cars.E_class(
            14,
            "https://auto.ironhorse.ru/wp-content/uploads/2016/09/E-Class-W213-550x309.jpg",
            "Mersedes",
            "E-CLASS",
            "3.5"

        ),
        Cars.E_class(
            15,
            "https://auto.ironhorse.ru/wp-content/uploads/2020/07/A6-C8-front-550x343.jpg",
            "AUDI",
            "A6",
            "3.0"
        ),
        Cars.J_class(
            16,
            "https://auto.ironhorse.ru/wp-content/uploads/2013/11/Prado-150-2009-550x343.jpg",
            "Toyota",
            "Prado 150",
            "4.0",
            250

        ),
        Cars.J_class(
            17,
            "https://auto.ironhorse.ru/wp-content/uploads/2018/01/W464-550x343.jpg",
            "Mersedes",
            "G-CLASS",
            "4.0",
            256
        ),
        Cars.J_class(
            18,
            "https://auto.ironhorse.ru/wp-content/uploads/2019/09/lr-defender-2-110-front-550x343.jpg",
            "LAND ROVER",
            "Defender",
            "3.0",
            291
        )

    )

    fun getCarList(): List<Cars> {
        return carsList
    }

    fun deleteCar(repositoriyCarList: List<Cars>, position: Int): List<Cars> {

        return repositoriyCarList.filterIndexed { index, cars -> index != position }
    }

    fun generateCar(
        newCarClass: String,
        newCarImageLinc: String,
        newCarBrend: String,
        newCarName: String,
        newCarEngineCapacity: String,
        newCarSpecial: String
    ): Cars {

        return when (newCarClass) {
            "Класс A" -> Cars.A_class(
                Random.nextLong(),
                newCarImageLinc,
                newCarBrend,
                newCarName,
                newCarEngineCapacity,
                newCarSpecial
            )

            "Класс B" -> Cars.B_class(
                Random.nextLong(),
                newCarImageLinc,
                newCarBrend,
                newCarName,
                newCarEngineCapacity,
                newCarSpecial.toInt()
            )
            "Класс C" -> Cars.C_class(
                Random.nextLong(),
                newCarImageLinc,
                newCarBrend,
                newCarName,
                newCarEngineCapacity,
                newCarSpecial.toInt()
            )
            "Класс D" -> Cars.D_class(
                Random.nextLong(),
                newCarImageLinc,
                newCarBrend,
                newCarName,
                newCarEngineCapacity,
                newCarSpecial.toInt()
            )
            "Класс E" -> Cars.E_class(
                Random.nextLong(), newCarImageLinc, newCarBrend, newCarName, newCarEngineCapacity
            )
            "Класс J" -> Cars.J_class(
                Random.nextLong(),
                newCarImageLinc,
                newCarBrend,
                newCarName,
                newCarEngineCapacity,
                newCarSpecial.toInt()
            )
            else -> error("class: CarClassDialogFragment || fun: onActivityCreated carClass = $newCarClass not fund")
        }
    }
}
