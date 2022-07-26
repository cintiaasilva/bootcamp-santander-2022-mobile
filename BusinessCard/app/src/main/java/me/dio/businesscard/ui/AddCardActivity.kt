package me.dio.businesscard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import me.dio.businesscard.App
import me.dio.businesscard.R
import me.dio.businesscard.data.BusinessCard
import me.dio.businesscard.databinding.ActivityAddCardBinding

class AddCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddCardBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners() {
        binding.ibClose.setOnClickListener {
            finish()
        }

        binding.btnConfirmar.setOnClickListener {

            val businessCard = BusinessCard(
                nome = binding.tilName.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                telefone = binding.tilPhone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                cor = binding.tilCor.editText?.text.toString(),
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(this, R.string.label_show_sucess, Toast.LENGTH_SHORT).show()
        }

    }
}