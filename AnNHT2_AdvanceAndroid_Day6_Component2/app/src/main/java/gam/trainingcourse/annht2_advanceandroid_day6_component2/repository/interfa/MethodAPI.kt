package gam.trainingcourse.annht2_advanceandroid_day6_component2.repository.interfa

import gam.trainingcourse.annht2_advanceandroid_day6_component2.model.ListMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MethodAPI {

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") key: String): Call<ListMovies>

    @GET("/3/movie/top_rated")
    fun getTopRatingMovies(@Query("api_key") key: String): Call<ListMovies>

}