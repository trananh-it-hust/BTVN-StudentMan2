package com.example.studentmanagement

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import android.widget.EditText
import android.widget.Button



class AddEditStudentFragment : Fragment() {
    private lateinit var editTextName: EditText
    private lateinit var editTextId: EditText
    private lateinit var btnSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_student, container, false)
        editTextName = view.findViewById(R.id.edit_text_name)
        editTextId = view.findViewById(R.id.edit_text_id)
        btnSave = view.findViewById(R.id.btn_save)

        val args = AddEditStudentFragmentArgs.fromBundle(requireArguments())
        editTextName.setText(args.name)
        editTextId.setText(args.id)

        btnSave.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }
}
