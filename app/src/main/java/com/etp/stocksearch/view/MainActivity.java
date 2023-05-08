package com.etp.stocksearch.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.etp.stocksearch.R;
import com.etp.stocksearch.data.model.LSThreeCorporationModel;
import com.etp.stocksearch.viewmodel.MainPageViewModel;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {

    private MainPageViewModel mMainPageViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMainPageViewModel = new ViewModelProvider(this).get(MainPageViewModel.class);
        if (mMainPageViewModel.isSetUp()) {

            subscribeSubject();
        }

    }

    private void subscribeSubject() {
        {
            Disposable disposable = mMainPageViewModel.mOutput.showRecyclerView
                    .subscribe(new Consumer<List<LSThreeCorporationModel>>() {
                        @Override
                        public void accept(List<LSThreeCorporationModel> threeCorporationModels) throws Exception {
                            mAdapter = new RecyclerViewAdapter(threeCorporationModels);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });
            addDisposable(disposable);
        }
        mMainPageViewModel.mInput.callGetCorporation.onNext("20230508");

    }

    private class RecyclerViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter {

        private List<LSThreeCorporationModel> threeCorporationModelList;

        public RecyclerViewAdapter(List<LSThreeCorporationModel> threeCorporationModelList) {
            this.threeCorporationModelList = threeCorporationModelList;
        }

        @NonNull
        @Override
        public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
            RecyclerViewHolder holder = new RecyclerViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {

            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.setItem(getItem(position));
        }

        @Override
        public int getItemCount() {
            return threeCorporationModelList.size();
        }

        public LSThreeCorporationModel getItem(int position) {
            return threeCorporationModelList.get(position);
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mStockIDTextView;
        private TextView mStockNameTextView;
        private TextView mTransVolumeTextView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mStockIDTextView = itemView.findViewById(R.id.stock_id_text_view);
            mStockNameTextView = itemView.findViewById(R.id.stock_name_text_view);
            mTransVolumeTextView = itemView.findViewById(R.id.over_number_text_view);
        }

        public void setItem(LSThreeCorporationModel item) {

            mStockIDTextView.setText(item.getStockID());
            mStockNameTextView.setText(item.getStockName());
            mTransVolumeTextView.setText(item.getTransVolume());
        }
    }


}