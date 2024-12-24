package com.example.studentmanagement

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class StudentListFragment : Fragment(), View.OnCreateContextMenuListener {
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val students = mutableListOf(
        Student("Nguyễn Văn A", "SV001"),
        Student("Trần Thị B", "SV002")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        listView = view.findViewById(R.id.list_view_students)
        adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            students.map { "${it.name} (${it.id})" }
        )
        listView.adapter = adapter

        // Context menu
        registerForContextMenu(listView)
        listView.setOnCreateContextMenuListener(this)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add_new) {
            findNavController().navigate(R.id.action_studentListFragment_to_addEditStudentFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo
    ) {
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when (item.itemId) {
            R.id.menu_edit -> {
                val action = StudentListFragmentDirections
                    .actionStudentListFragmentToAddEditStudentFragment(
                        students[info.position].name, students[info.position].id
                    )
                findNavController().navigate(action)
                return true
            }
            R.id.menu_remove -> {
                students.removeAt(info.position)
                adapter.notifyDataSetChanged()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}