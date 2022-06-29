package me.dio.matchsimulator.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import me.dio.matchsimulator.R
import me.dio.matchsimulator.data.MatchesApi
import me.dio.matchsimulator.databinding.ActivityMainBinding
import me.dio.matchsimulator.domain.Match
import me.dio.matchsimulator.ui.adapterView.MatchesAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private var matchesAdapter: MatchesAdapter = MatchesAdapter(Collections.emptyList())
    private lateinit var matchesApi: MatchesApi
    private lateinit var binding: ActivityMainBinding
    private lateinit var matches: List<Match>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupHttpClient() // retrofit

        setupMatchesList()
        setupMatchesRefresh()
        setupFloatingActionButton()
    }

    private fun setupHttpClient() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://cintiaasilva.github.io/matches-simulator-api/")
            .addConverterFactory(GsonConverterFactory.create()) // (des)serializacao
            .build()

        matchesApi = retrofit.create() // chamada da interface (API)
    }

    private fun setupMatchesList() {
        // config no recyclerView pegando o elemento de binding
        binding.rvMatches.setHasFixedSize(true) // tamanho fixo
        binding.rvMatches.layoutManager = LinearLayoutManager(this)
        binding.rvMatches.adapter = matchesAdapter // ajuste realizado p/ se acaso o celular ficar sem internet o Refresh não some
        findMatchesFromApi()
    }

    private fun setupMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener(this::findMatchesFromApi)
    }

    private fun setupFloatingActionButton() {
        binding.fabSimulate.setOnClickListener { view ->
            view.animate().rotationBy(360F).setDuration(1000)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        val random = Random()
                        for ( i in 0 until matchesAdapter.itemCount){
                            val match: Match = matchesAdapter.matches[i]
                            match.homeTeam.score = random.nextInt(match.homeTeam.stars+1)
                            match.awayTeam.score = random.nextInt(match.awayTeam.stars+1)
                            matchesAdapter.notifyItemChanged(i)// notifica que foi atualizado
                        }
                    }
                })
        }
    }




    //////////////////////////////////////////////////////////////////
    private fun findMatchesFromApi() {
        binding.srlMatches.isRefreshing = true

        matchesApi.matches.enqueue(object : Callback<List<Match>> {


            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    matches = response.body()!!
                    matchesAdapter = MatchesAdapter(matches)
                    binding.rvMatches.adapter = matchesAdapter
                } else {
                    showErrorMessage()
                }
                binding.srlMatches.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage()
                binding.srlMatches.isRefreshing = false
            }

        })
    }

    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_LONG).show()
    }
}
