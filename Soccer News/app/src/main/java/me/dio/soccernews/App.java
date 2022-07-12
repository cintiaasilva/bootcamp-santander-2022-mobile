package me.dio.soccernews;

import android.app.Application;

/**
 * FIXME Centralizamos uma instancia do contexto em nosso {@link App}(mesmo sendo um "anti-pattern")
 * para extrairmos o máximo dos nossos ViewModels e camada de acesso á dados. Entretando, apesar das
 * nossas camadas ficarem mais coesas, o ideal seria usar uma solução de injeção de dependências
 * (como Dagger ou o Hilt).
 * @see <a href="https://stackoverflow.com/a/14057777/3072570">Android Singleton with Global Context</a>
 * */
public class App extends Application {
    private static App instance;

    public static  App getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }
}
