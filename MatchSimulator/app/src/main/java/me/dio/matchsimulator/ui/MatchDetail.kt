package me.dio.matchsimulator.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import me.dio.matchsimulator.databinding.ActivityMatchDetailBinding
import me.dio.matchsimulator.domain.Match

class MatchDetail : AppCompatActivity() {

    object Extras { // encapsula uma constante de partida
        const val MATCH = "EXTRA_MATCH"
    }

    private lateinit var binding: ActivityMatchDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //carregar o extra
        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        //os extras fica dentro da intent, pegar a partida
        //.let se tudo isso der certo esse será o contexto de obtenção da Partida
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            //pegando a imagem do local da partida e renderizando com o binding
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            //Irá aparecer o nome do estádio no ActionBar (topo)
            supportActionBar?.title = it.place.name

            binding.tvDescription.text = it.description

            Glide.with(this).load(it.homeTeam.image).into(binding.ivHomeTeam)
            binding.tvHomeTeamName.text = it.homeTeam.name
            binding.rbHomeTeamStars.rating = it.homeTeam.stars.toFloat()
            if (it.homeTeam.score != null) {
                binding.tvHomeTeamScore.text = it.homeTeam.score.toString()
            }

            Glide.with(this).load(it.awayTeam.image).into(binding.ivAwayTeam)
            binding.tvAwayTeamName.text = it.awayTeam.name
            binding.rbAwayTeamStars.rating = it.awayTeam.stars.toFloat()
            if (it.awayTeam.score != null) {
                binding.tvAwayTeamScore.text = it.awayTeam.score.toString()
            }
        }
    }
}