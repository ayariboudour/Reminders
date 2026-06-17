package com.boudour.reminders

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.boudour.reminders.databinding.DialogEditReminderBinding
import com.boudour.reminders.databinding.FragmentGeneralBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class GeneralFragment : Fragment() {

    private lateinit var binding: FragmentGeneralBinding

    private val preferences by lazy {
        requireActivity().getSharedPreferences(
            "general",
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeneralBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValue()
        binding.cardViewBinDay.setOnClickListener {
            showEditDialog(PREF_BIN_DAY)
        }
        binding.cardViewInsuranceNumber.setOnClickListener {
            showEditDialog(PREF_INSURANCE_NUMBER)
        }
        binding.cardViewWeddingAnniversary.setOnClickListener {
            showEditDialog(PREF_WEDDING_ANNIVERSARY)
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
        binding.textViewBinDayValue.text = preferences.getString(PREF_BIN_DAY, null)
        binding.textViewInsuranceNumberValue.text = preferences.getString(PREF_INSURANCE_NUMBER, null)
        binding.textViewWeddingAnniversaryValue.text = preferences.getString(PREF_WEDDING_ANNIVERSARY, null)
    }
    companion object {
        const val PREF_BIN_DAY = "pref_bin_day"
        const val PREF_INSURANCE_NUMBER = "pref_insurance_number"
        const val PREF_WEDDING_ANNIVERSARY = "pref_wedding_anniversary"
    }
}