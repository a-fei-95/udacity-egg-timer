/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.databinding.FragmentEggTimerBinding

class EggTimerFragment : Fragment() {

    private lateinit var binding: FragmentEggTimerBinding
    private val viewModel: EggTimerViewModel by viewModels()

    private val notificationManager by lazy {
        requireActivity().getSystemService(NotificationManager::class.java)
    }

    private val TOPIC = "breakfast"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEggTimerBinding.inflate(inflater)

        binding.run {
            eggTimerViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        createChannel(
            getString(R.string.egg_notification_channel_id),
            getString(R.string.egg_notification_channel_name)
        )

        return binding.root
    }

    private fun createChannel(channelId: String, channelName: String) {
        val notificationChannel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
            description = "Time for breakfast"
            setShowBadge(false)
        }

        notificationManager.createNotificationChannel(notificationChannel)
    }

    companion object {
        fun newInstance() = EggTimerFragment()
    }
}

