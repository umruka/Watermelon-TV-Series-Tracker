package com.example.tvtracker.Repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.tvtracker.Models.TvShow;
import com.example.tvtracker.Models.TvShowEpisode;
import com.example.tvtracker.Models.TvShowGenre;
import com.example.tvtracker.Models.TvShowPicture;
import com.example.tvtracker.Models.QueryModels.TvShowFull;

import java.util.List;

@Dao
public interface AppDao {


    //TvShow
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShow(TvShow tvShow);

    @Query("DELETE FROM tv_show_table")
    void deleteAllTvShows();

    @Query("DELETE FROM tv_show_table WHERE tv_show_id IN (:id)")
    void deleteTvShowById(int id);

    @Query("UPDATE tv_show_table SET tv_show_name=:name, tv_show_status=:status WHERE tv_show_id IN(:tvShowId)")
    void updateTvShow(int tvShowId, String name, String status);

    @Query("UPDATE tv_show_table SET tv_show_description=:description, tv_show_youtube_link=:youtubeLink, tv_show_rating=:rating WHERE tv_show_id IN(:tvShowId)")
    void updateTvShowDetails(int tvShowId, String description, String youtubeLink, String rating);

    @Query("UPDATE tv_show_table SET tv_show_flag=:watchingId WHERE tv_show_id IN(:id)")
    void updateTvShowWatchingFlag(int id, String watchingId);

    @Query("SELECT * FROM tv_show_table WHERE tv_show_id=:Id")
    TvShow getTvShowById(int Id);

    @Query("SELECT * FROM tv_show_table")
    LiveData<List<TvShow>> getAllTvShows();

    //Watchlist
    @Query("SELECT * FROM tv_show_table WHERE tv_show_flag=:flag")
    LiveData<List<TvShow>> getWatchlistTvShows(String flag);

    //TvShowPicture

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void insertTvShowPicture(TvShowPicture tvShowPicture);

    @Query("SELECT * FROM tv_show_picture_table WHERE tv_show_id IN (:tvShowId)")
    List<TvShowPicture>  getTvShowPicturesByTvShowId(int tvShowId);


    //TvShowEpisode
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShowEpisode(TvShowEpisode tvShowEpisode);

    @Query("SELECT * FROM tv_show_episode_table WHERE tv_show_id IN (:tvShowId)")
    List<TvShowEpisode> getTvShowEpisodesById(int tvShowId);

    //TvShowGenre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShowGenre(TvShowGenre tvShowGenre);

    @Query("SELECT * FROM tv_show_genre_table WHERE tv_show_id IN (:tvShowId)")
    List<TvShowGenre> getTvShowGenresById(int tvShowId);


    //TvShowFull
    @Transaction
    @Query("SELECT * FROM tv_show_table WHERE tv_show_id IN (:tvShowId)")
    List<TvShowFull> getTvShowWithPicturesAndEpisodesById(int tvShowId);


}
