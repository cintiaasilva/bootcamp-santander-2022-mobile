package me.dio.soccernews.ui.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import me.dio.soccernews.data.local.AppDatabase;
import me.dio.soccernews.databinding.FragmentNewsBinding;
import me.dio.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private AppDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = Room.databaseBuilder(getContext(),AppDatabase.class, "soccer-news")
                .allowMainThreadQueries()
                .build();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
                binding.rvNews.setAdapter(new NewsAdapter(news, updatedNews -> {

                    db.newsDAO().save(updatedNews);

                }));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

//AsyncTask.execute(() -> db.newsDAO().save(updatedNews)); // vai executar de forma assincrona a persistencia no banco ROM

// Erro: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
// Solução: AsyncTask.execute(())
// Referência: https://stackoverflow.com/questions/59607324/error-cannot-access-database-on-the-main-thread-since-it-may-potentially-lock-t

//OU

// Solução: .allowMainThreadQueries()

//O ideal é utilizar liveData.