package me.dio.matchsimulator.ui.adapterView;
//https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=pt-br - Criar listas
//dinâmicas com o RecyclerView
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.dio.matchsimulator.databinding.MatchItemBinding;
import me.dio.matchsimulator.domain.Match;
import me.dio.matchsimulator.ui.MatchDetail;

// classe responsavel por adaptarmos as informações para o RecyclerView
// neste projeto as informações seria no caso a lista de partidas
public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private final List<Match> matches;

    // para o adapter funcionar ele recebe as partidas como parametro
    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() { return matches;}

    @NonNull
    @Override // instancia o viewholder para isso precisa do binding
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // recupero o layoutInflater através do contexto do ViewGroup
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MatchItemBinding binding = MatchItemBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matches.get(position); // partida que está rolando
        Context context = holder.itemView.getContext();

        // Adapta os dados da partida (recuperada da API) para o nosso layout.
        Glide.with(context).load(match.getHomeTeam().getImage()).circleCrop().into(holder.binding.ivHomeTeam);
        holder.binding.tvHomeTeamName.setText(match.getHomeTeam().getName());
        if (match.getHomeTeam().getScore() != null) {
            holder.binding.tvHomeTeamScore.setText(String.valueOf(match.getHomeTeam().getScore()));
        }

        Glide.with(context).load(match.getAwayTeam().getImage()).circleCrop().into(holder.binding.ivAwayTeam);
        holder.binding.tvAwayTeamName.setText(match.getAwayTeam().getName());
        if (match.getAwayTeam().getScore() != null) {
            holder.binding.tvAwayTeamScore.setText(String.valueOf(match.getAwayTeam().getScore()));
        }

        holder.itemView.setOnClickListener(view -> { // é aqui que será trafegado para a tela de Detalhes
            Intent intent = new Intent(context, MatchDetail.class); //intenção de redirecionamento
            intent.putExtra(MatchDetail.Extras.MATCH, match);// dados que será trafegado da constante de Detalhes
            context.startActivity(intent); // startando o contexto
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

//////////////////////////////////////////////////////////////////////////////////////////

    public static class ViewHolder extends RecyclerView.ViewHolder { // inner class obrigatória

        private final MatchItemBinding binding;

        public ViewHolder(MatchItemBinding matchItemBinding) {
            super(matchItemBinding.getRoot());
            this.binding = matchItemBinding;
        }
    }
}
