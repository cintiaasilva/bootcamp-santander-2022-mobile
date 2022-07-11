package me.dio.soccernews.ui.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import me.dio.soccernews.data.remote.SoccerNewsApi;
import me.dio.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// ViewModel é um componente que lida de forma otimizada com relação ao ciclo de vida das activity/fragmentos

// LiveData que retorna a lista de noticias
public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news =  new MutableLiveData<>();
    private final SoccerNewsApi api;


    public NewsViewModel() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cintiaasilva.github.io/soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SoccerNewsApi.class);
        findNews();
    }

    public void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()){
                    news.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {

            }
        });
    }

    // LiveData cria gatilhos da atualização da ui (elementos de tela) é um objeto observable
    public LiveData<List<News>> getNews() { return this.news; }
}