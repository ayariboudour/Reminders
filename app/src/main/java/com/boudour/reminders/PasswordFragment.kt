package com.boudour.reminders

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.boudour.reminders.databinding.DialogEditReminderBinding
import com.boudour.reminders.databinding.PasswordFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PasswordFragment : Fragment() {

    private lateinit var binding: PasswordFragmentBinding
    private val preferences by lazy {
        requireActivity().getSharedPreferences(
            "password",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValue()
        binding.cardViewWifi.setOnClickListener {
            showEditDialog(PREF_WIFI)
        }
        binding.cardViewTabletPin.setOnClickListener {
            showEditDialog(PREF_TABLET_PIN)
        }
        binding.cardViewBikeLock.setOnClickListener {
            showEditDialog(PREF_BIKE_LOCK)
        }

    }

    private fun showEditDialog(prefKey: String) {
        val dialogBinding = DialogEditReminderBinding.inflate(requireActivity().layoutInflater)
        dialogBinding.editReminderInput.setText(preferences.getString(prefKey, null))
        MaterialAlertDialogBuilder(requireContext())
            .setView(dialogBinding.root)
            .setTitle("Update Value")
            .setPositiveButton("Save") { dialog, _ ->
                preferences.edit {
                    putString(prefKey,dialogBinding.editReminderInput.text?.toString())
                }
                displayValue()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun displayValue() {
        binding.textViewWifiValue.text = preferences.getString(PREF_WIFI, null)
        binding.textViewTabletPinValue.text = preferences.getString(PREF_TABLET_PIN, null)
        binding.textViewBikeLockValue.text = preferences.getString(PREF_BIKE_LOCK, null)
    }

    companion object {
        const val PREF_WIFI = "pref_wifi"
        const val PREF_TABLET_PIN = "pref_tablet_pin"
        const val PREF_BIKE_LOCK = "pref_bike_lock"
    }
}