package com.etp.stocksearch.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import com.etp.stocksearch.custom.application.StockSearchApplication;
import com.etp.stocksearch.data.model.LSApiResponse;
import com.etp.stocksearch.data.model.LSThreeCorporationModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageViewModel extends BaseViewModel {

    public Input mInput = new Input();
    public Output mOutput = new Output();

    public boolean isSetUp() {

        boolean result = false;

        try {

            {
                Disposable disposable = mInput.callGetCorporation
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String dateString) throws Exception {
                                try {

                                    Callback<LSApiResponse> apiCallback = new Callback<LSApiResponse>() {
                                        @Override
                                        public void onResponse(Call<LSApiResponse> call, Response<LSApiResponse> response) {

                                            if (TextUtils.equals(response.body().getmStatus(), "OK")) {
                                                List<List<String>> threeCorporationList = response.body().getmThreeCorporationList();
                                                List<LSThreeCorporationModel> perStockInfoList = new ArrayList<>();
                                                for (List<String> threeCorporationItem : threeCorporationList) {
                                                    LSThreeCorporationModel dataModel = new LSThreeCorporationModel();
                                                    dataModel.setStockID(threeCorporationItem.get(0));
                                                    dataModel.setStockName(threeCorporationItem.get(1).trim());
                                                    dataModel.setTransVolume(threeCorporationItem.get(threeCorporationItem.size() - 1).replace(",", ""));
                                                    perStockInfoList.add(dataModel);
                                                }

                                                Collections.sort(perStockInfoList, new Comparator<LSThreeCorporationModel>() {
                                                    @Override
                                                    public int compare(LSThreeCorporationModel t1, LSThreeCorporationModel dataModel) {
                                                        return Integer.compare(Integer.parseInt(dataModel.getTransVolume()), Integer.parseInt(t1.getTransVolume()));
                                                    }
                                                });
                                                mOutput.showRecyclerView.onNext(perStockInfoList);
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<LSApiResponse> call, Throwable t) {

                                        }
                                    };


                                    StockSearchApplication.getInstance()
                                            .getBaseStockApiManager()
                                            .getmApi()
                                            .callLSStockThreeCorporation("json", dateString, "ALL")
                                            .enqueue(apiCallback);
                                } catch (Exception e) {

                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                            }
                        });
                addDisposable(disposable);
            }
            result = true;
        } catch (Exception e) {

        }
        return result;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public class Input {

        public BehaviorSubject<String> callGetCorporation = BehaviorSubject.create();
    }

    public class Output {

        public BehaviorSubject<List<LSThreeCorporationModel>> showRecyclerView = BehaviorSubject.create();
    }


}
