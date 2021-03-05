package com.skillbox.multithreading

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.race_condition_fragment.*

class RaceConditionFragment : Fragment(R.layout.race_condition_fragment) {

    private var value: Int = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        button1.setOnClickListener {
            multiThraidingRaceCondition()
        }

        button2.setOnClickListener {
            multiThraidingRaceConditionSynhron()
        }
    }

    private fun multiThraidingRaceCondition() {

        val thredCount = edit_text1.lineCount
        val incrimentCount = edit_text2.lineCount
        val expectedValue = value + thredCount * incrimentCount
        val timeStart = System.currentTimeMillis()

        (0 until thredCount).map {
            Thread {

                for (i in 0 until incrimentCount) {
                    value++
                }
            }.apply {
                start()
            }
        }
            .map { it.join() }

        val timeEnd = System.currentTimeMillis()
        textView.text = value.toString() + " expected = $expectedValue time = ${timeEnd - timeStart}"
    }

    private fun multiThraidingRaceConditionSynhron() {

        val thredCount = 100
        val incrimentCount = 1000000
        val expectedValue = value + thredCount * incrimentCount
        val timeStart = System.currentTimeMillis()

        (0 until thredCount).map {
            Thread {
                synchronized(this) {
                    for (i in 0 until incrimentCount) {
                        value++
                    }
                }
            }.apply {
                start()
            }
        }
            .map { it.join() }

        val timeEnd = System.currentTimeMillis()
        textView.text = value.toString() + " expected = $expectedValue time = ${timeEnd - timeStart}"
    }
}
