package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.domain.News;

// ViewModel é um componente que lida de forma otimizada com relação ao ciclo de vida das activity/fragmentos

// LiveData que retorna a lista de noticias
public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        news = new MutableLiveData<>();

        //TODO: Remover mock de noticias
        //Mock de dados
        List<News> news = new ArrayList<>();
        news.add(new News("Brasil é vencedor novamente", "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        news.add(new News("Brasil vai jogar neste domingo","Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        news.add(new News("Brasil empata com Japão","Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));

        this.news.setValue(news);
    }

    // LiveData cria gatilhos da atualização da ui (elementos de tela) é um objeto observable
    public LiveData<List<News>> getNews() { return this.news; }
}