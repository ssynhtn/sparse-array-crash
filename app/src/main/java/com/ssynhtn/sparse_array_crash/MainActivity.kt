package com.ssynhtn.sparse_array_crash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LongSparseArray
import com.ssynhtn.sparse_array_crash.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val map = LongSparseArray<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd22.setOnClickListener {
            for (i in 0 until 22) {
                map.put(i.toLong(), i.toLong())
            }
        }

        binding.btnThread1.setOnClickListener {
            Thread {
                val key = 22L
                map.put(key, key)
            }.let {
                it.name = "my-1"
                it.start()
            }
        }

        binding.btnThread2.setOnClickListener {
            Thread {
                map.put(22L, 22L)
                map.put(23L, 23L)
                map.put(24L, 24L)
            }.let {
                it.name = "my-2"
                it.start()
            }
        }

    }
}