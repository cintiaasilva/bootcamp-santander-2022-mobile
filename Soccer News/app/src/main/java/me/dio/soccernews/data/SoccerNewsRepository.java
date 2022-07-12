package me.dio.soccernews.data;
//classe que oferece a instancia do bd local e da api remoto

import androidx.room.Room;

import me.dio.soccernews.App;
import me.dio.soccernews.data.local.AppDatabase;
import me.dio.soccernews.data.remote.SoccerNewsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SoccerNewsRepository {

    //region Constantes
    private static final String REMOTE_API_URL = "https://cintiaasilva.github.io/soccer-news-api/";
    private static final String LOCAL_DB_NAME  = "soccer-news";
    //endregion

    //region Atributos: encapsulam o acesso a nossa API (Retrofit) e banco de dados local (Room).
    private SoccerNewsApi api;
    private AppDatabase db;

    public SoccerNewsApi getApi() { return api; }
    public AppDatabase getDb() { return db; }
    //endregion

    //region Singleon: garante uma instância única dos atributos relacionados ao Retrofit e Room
    private SoccerNewsRepository(){
        api = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);

        db = Room.databaseBuilder(App.getInstance(), AppDatabase.class, LOCAL_DB_NAME).build();
    }
    public static SoccerNewsRepository getInstance(){ return LazyHolder.INSTANCE;}

    private static class LazyHolder{
        private static final SoccerNewsRepository INSTANCE = new SoccerNewsRepository();
    }
    //endregion
}
