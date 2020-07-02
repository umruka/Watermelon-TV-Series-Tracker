package com.watermelon.UI.Details;

import android.app.Application;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.watermelon.Repository.AppRepoHelpClasses.Resource;
import com.watermelon.Models.TvSeriesFull;
import com.watermelon.Repository.AppRepository;

import java.util.List;

public class DetailsViewModel extends AndroidViewModel {
    private AppRepository repository;
    private final LiveData<Resource<TvSeriesFull>> detailsObservable;
    private MutableLiveData tvShowId = new MutableLiveData();

    public DetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
        detailsObservable = Transformations.switchMap(tvShowId, id -> repository.fetchTvSeriesDetails((Integer) tvShowId.getValue()));

    }

    void setTvShowId(int id) {
        tvShowId.setValue(id);
    }

    LiveData<Resource<TvSeriesFull>> getDetailsObservable() {
        return detailsObservable;
    }

    void changeAllSeasonsWatchedFlag(Pair<List<Integer>, Boolean> params) {
        repository.setTvShowAllSeasonWatched(params);
    }

    void changeTvShowWatchedFlag(Pair<Integer, Boolean> params) {
        repository.setTvSeriesWatchedFlag(params);
    }


}