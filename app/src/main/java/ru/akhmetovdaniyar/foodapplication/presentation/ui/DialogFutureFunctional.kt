package ru.akhmetovdaniyar.foodapplication.presentation.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ru.akhmetovdaniyar.foodapplication.databinding.DialogFutureBinding

class DialogFutureFunctional: DialogFragment() {

    private var _binding: DialogFutureBinding? = null
    private val binding get() = _binding!!


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogFutureBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext())
        dialog.setContentView(binding.root)

        binding.buttonClose.setOnClickListener {
            dismiss()
        }

        return dialog
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}