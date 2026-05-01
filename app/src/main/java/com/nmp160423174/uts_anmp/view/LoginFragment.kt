package com.nmp160423174.uts_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.nmp160423174.uts_anmp.R
import com.nmp160423174.uts_anmp.databinding.FragmentLoginBinding
import com.nmp160423174.uts_anmp.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            viewModel.login(username, password)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.statusLoginLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                val action = LoginFragmentDirections.actionHabitListFragment()
                binding.root.findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        })
    }
}