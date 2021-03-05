package com.skillbox.multithreading

import android.util.Log
import androidx.fragment.app.Fragment

class DeadlockFragment : Fragment() {

    private var i = 0

    override fun onResume() {
        super.onResume()

        val friend1 = Person("Вася")
        val friend2 = Person("Петя")

        val thread1 = Thread {
            Log.d("Deadlock", "Start1")

            (0..1000000).forEach {
                friend1.throwBallTo(friend2)
                i++
            }
            Log.d("Deadlock", "End1")
        }

        val thread2 = Thread {
            Log.d("Deadlock", "Start2")
            (0..1000000).forEach {

                friend2.throwBallTo(friend1)
                i++
            }

            Log.d("Deadlock", "End2")
        }

        thread1.start()
        thread2.start()
    }

    data class Person(
        val name: String
    ) {

        fun throwBallTo(friend: Person) {
            synchronized(this) {
                Log.d(
                    "Person",
                    "$name бросает мяч ${friend.name} на потоке ${Thread.currentThread().name}"
                )
                Thread.sleep(500)
            }
            friend.throwBallTo(this)
        }
    }
}
