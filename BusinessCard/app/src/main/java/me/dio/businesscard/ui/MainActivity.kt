package me.dio.businesscard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import me.dio.businesscard.App
import me.dio.businesscard.databinding.ActivityMainBinding
import me.dio.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getAllBusinessCard()
        binding.rvItemCard.adapter = adapter
        addListener()

    }

    //assim q clicar vai para a tela de cadastrar um cartão de visitas
    private fun addListener() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddCardActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerShare = { card ->
            Image.share(this, card)

        }
    }

    private fun getAllBusinessCard(){
        mainViewModel.getAll().observe(this) { businessCard ->
            adapter.submitList(businessCard)
        }
    }
}