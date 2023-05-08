package com.etp.stocksearch.view;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class BaseActivity extends AppCompatActivity {

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
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            Timber.d("///" + "Before disposable size: " + compositeDisposable.size());
            compositeDisposable.dispose();
            Timber.d("///" + "After disposable size: " + compositeDisposable.size());
            compositeDisposable = null;
        }
    }
}
