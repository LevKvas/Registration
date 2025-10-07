package com.example.application_1.fragment_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.application_1.R

class SecondFragment : Fragment() {

    // ViewModel using delegate
    private val viewModel: SecondFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initialisation elements
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        val textView = view.findViewById<TextView>(R.id.textView)
        val successImage = view.findViewById<ImageView>(R.id.successImage)
        val backButton = view.findViewById<Button>(R.id.button)

        // setting observers
        setupObservers(toolbar, textView)
        setupClickListeners(backButton)

        // load data
        viewModel.loadUserData()
    }

    private fun setupObservers(toolbar: Toolbar, textView: TextView) {
        // look at the titles
        viewModel.screenTitle.observe(viewLifecycleOwner) { title ->
            toolbar.title = title
        }

        // look at the messages
        viewModel.successMessage.observe(viewLifecycleOwner) { message ->
            textView.text = message
        }

        // Monitoring navigation events
        viewModel.navigationEvent.observe(viewLifecycleOwner) { shouldGoBack ->
            if (shouldGoBack) {
                parentFragmentManager.popBackStack()
                viewModel.navigationHandled()
            }
        }
    }

    private fun setupClickListeners(backButton: Button) {
        backButton.setOnClickListener {
            // Instead of direct navigation, we notify the ViewModel
            viewModel.onBackButtonClicked()
        }
    }
}