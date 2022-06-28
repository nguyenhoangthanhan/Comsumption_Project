package gam.trainingcourse.annht2_advanceandroid_day6_component2.view_model

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log

import android.widget.Toast

import androidx.lifecycle.ViewModel
import gam.trainingcourse.annht2_advanceandroid_day6_component2.R
import gam.trainingcourse.annht2_advanceandroid_day6_component2.adapter.MovieAdapter
import gam.trainingcourse.annht2_advanceandroid_day6_component2.model.Genre
import gam.trainingcourse.annht2_advanceandroid_day6_component2.model.ListMovies
import gam.trainingcourse.annht2_advanceandroid_day6_component2.model.Movie
import gam.trainingcourse.annht2_advanceandroid_day6_component2.repository.API
import gam.trainingcourse.annht2_advanceandroid_day6_component2.repository.interfa.MethodAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForYouViewModel : ViewModel() {
    private val request = API.buildService(MethodAPI::class.java)
    // avoid add data again when rotate screen
    private var isInitPopularMovie = false
    private var isInitTopRatedMovie = false
    private var isInitGenres = false

    val popularMovies = mutableListOf<Movie>()
    val topRatingMovies = mutableListOf<Movie>()

    val genres = mutableListOf<Genre>()

    fun initPopularMovie(context: Context, popularMoviesAdapter: MovieAdapter) {
        if (!isInitPopularMovie) {
            val call1 = request.getPopularMovies(context.getString(R.string.api_key))

            call1.enqueue(object : Callback<ListMovies> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<ListMovies>, response: Response<ListMovies>) {
                    if (response.isSuccessful) {
                        Log.d("api_data", response.body().toString())
                        val listMovies: ListMovies? = response.body()
                        if (listMovies != null){
                            popularMovies.addAll(listMovies.results)
                            popularMoviesAdapter.notifyDataSetChanged()
                        }

                        isInitPopularMovie = true
                    }
                }

                override fun onFailure(call: Call<ListMovies>, t: Throwable) {
                    Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }

    fun initTopRatedMovie(context: Context, topRatedMoviesAdapter: MovieAdapter) {
        if (!isInitTopRatedMovie) {
            val call2 = request.getTopRatingMovies(context.getString(R.string.api_key))
            call2.enqueue(object : Callback<ListMovies> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<ListMovies>, response: Response<ListMovies>) {
                    if (response.isSuccessful) {
                        Log.d("api_data", response.body().toString())
                        val listMovies: ListMovies? = response.body()
                        if (listMovies != null){
                            topRatingMovies.addAll(listMovies.results)
                            topRatedMoviesAdapter.notifyDataSetChanged()
                        }
                        isInitTopRatedMovie = true
                    }
                }

                override fun onFailure(call: Call<ListMovies>, t: Throwable) {
                    Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    fun initGenres(context: Context) {
        if (!isInitGenres) {
            val genreName = context.resources.getStringArray(R.array.genres)
            val genreUrl = context.resources.getStringArray(R.array.genreUrl)
            for (i in genreName.indices)
                genres.add(Genre(genreUrl[i], genreName[i]))
            isInitGenres = true
        }
    }

}