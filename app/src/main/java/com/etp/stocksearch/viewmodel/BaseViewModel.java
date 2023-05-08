package com.etp.stocksearch.viewmodel;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class BaseViewModel extends ViewModel {

    //To control disposable
    private CompositeDisposable compositeDisposable;

    /**
     * Add rxJava request
     */

    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);

        Timber.d("///" + "addDisposable size: " + compositeDisposable.size());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            Timber.d("///" + "Before disposable size: " + compositeDisposable.size());
            compositeDisposable.dispose();
            Timber.d("///" + "After disposable size: " + compositeDisposable.size());
            compositeDisposable = null;
        }
    }


}
