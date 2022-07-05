package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// ViewModel é um componente que lida de forma otimizada com relação ao ciclo de vida das activity/fragmentos
public class NewsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is news fragment");
    }

    // LiveData cria gatilhos da atualização da ui (elementos de tela) é um objeto observable
    public LiveData<String> getText() {
        return mText;
    }
}