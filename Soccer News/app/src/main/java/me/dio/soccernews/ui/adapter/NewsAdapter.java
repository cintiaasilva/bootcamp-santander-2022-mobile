package me.dio.soccernews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.dio.soccernews.R;
import me.dio.soccernews.databinding.NewsItemBinding;
import me.dio.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private FavoriteListener favoriteListener;

    public NewsAdapter(List<News> news, FavoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        News news = this.news.get(position);

        holder.binding.tvTitle.setText(news.getTitle());

        holder.binding.tvDescription.setText(news.getDescription());

        Picasso.get().load(news.getImage()).into(holder.binding.ivThumbnail);

        //Funcionalidade de abrir link

        holder.binding.btOpenLink.setOnClickListener(view ->{
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.getLink()));
            context.startActivity(i);
        });

        //Funcionalidade de compartilhar
        holder.binding.ivShare.setOnClickListener(view ->{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, news.getTitle()); //aparece o assunto
            intent.putExtra(Intent.EXTRA_TEXT, news.getLink());
            context.startActivity(Intent.createChooser(intent, "Share"));
            //createChooser = vai aparecer para o usuário escolher por qual meio ele quer
            //compartilhar (whats, gmail,...)
        });

        //Funcionalidade favorite (o evento será instanciado pelo Fragment)  //NewsFragment
        holder.binding.ivFavorite.setOnClickListener(v -> {
            news.favorite = !news.favorite; //inverte o favorite (se estiver favoritado, irá desfavoritar)
            this.favoriteListener.click(news);
            notifyItemChanged(position); //o RV vai refletir que a flag de favorito foi atribuida
        });

        //if ternário
        int favoriteColor = news.favorite ? R.color.favorite_active : R.color.favorite_inactive;

        holder.binding.ivFavorite.setColorFilter(context.getResources().getColor(favoriteColor));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }



    ////////////////////////////////////////////////////////////////////////

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    //Listener = inferface com os métodos que quero q implemente
    public interface FavoriteListener {
        void click(News news);
    }
}
