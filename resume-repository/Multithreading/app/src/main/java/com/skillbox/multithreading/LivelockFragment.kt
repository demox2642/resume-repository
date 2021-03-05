package com.skillbox.multithreading

import android.util.Log
import androidx.fragment.app.Fragment
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

class LivelockFragment : Fragment() {
    private val lock1: Lock = ReentrantLock(true)
    private val lock2: Lock = ReentrantLock(true)

    override fun onResume() {
        super.onResume()
        Thread { action1() }.start()
        Thread { action2() }.start()
    }

    fun action1() {
        try {
            while (true) {
                lock1.tryLock(50, TimeUnit.MILLISECONDS)
                Log.d("LivelockFragment", "Заблокирован поток 1")
                TimeUnit.MILLISECONDS.sleep(1000)
                if (lock2.tryLock()) {
                    Log.d("LivelockFragment", "Заблокирован поток 2")
                } else {
                    Log.d("LivelockFragment", "Не могу заблокировать поток 2, блокирую поток 1")
                    lock1.unlock()
                    continue
                }
                Log.d("LivelockFragment", "ACTION 1")
                break
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            lock1.unlock()
            lock2.unlock()
        }
    }

    fun action2() {
        try {
            while (true) {
                lock2.tryLock(50, TimeUnit.MILLISECONDS)
                Log.d("LivelockFragment", "Заблокирован поток 2")
                TimeUnit.MILLISECONDS.sleep(50)
                if (lock1.tryLock()) {
                    Log.d("LivelockFragment", "Заблокирован поток 1")
                } else {
                    Log.d("LivelockFragment", "Не могу заблокировать поток 1, блокирую поток 2")
                    lock2.unlock()
                    continue
                }
                Log.d("LivelockFragment", "ACTION 2")
                break
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            lock1.unlock()
            lock2.unlock()
        }
    }
}
