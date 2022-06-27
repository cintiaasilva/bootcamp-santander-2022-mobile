package me.dio.matchsimulator.data; // representação da API

import java.util.List;

import me.dio.matchsimulator.domain.Match;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesApi {

    @GET("matches.json")
    Call<List<Match>> getMatches();
}
