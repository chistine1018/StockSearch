package com.etp.stocksearch.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.etp.stocksearch.R;
import com.etp.stocksearch.custom.StockProperties;
import com.etp.stocksearch.data.model.LSStockDetail;
import com.etp.stocksearch.data.model.LSStockRangeInfoDetail;
import com.etp.stocksearch.viewmodel.MainPageViewModel;
import com.google.gson.Gson;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

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
            Disposable disposable = mMainPageViewModel.mOutput.showRecyclerView.subscribe(new Consumer<List<LSStockDetail>>() {
                @Override
                public void accept(List<LSStockDetail> stockEntityList) throws Exception {
                    Timber.d("///" + "ShowRecyclerView success");
                    mAdapter = new RecyclerViewAdapter(stockEntityList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {

                    Timber.d("///" + "ShowRecyclerView error");
                }
            });
            addDisposable(disposable);
        }

        {
            Disposable disposable = mMainPageViewModel.mOutput.changeToStockDetailPage.subscribe(new Consumer<LSStockDetail>() {
                @Override
                public void accept(LSStockDetail stockDetail) throws Exception {
                    StockDetailActivity.startActivity(MainActivity.this, stockDetail);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    Timber.d("///" + "changeToStockDetailPage disposable error: " + new Gson().toJson(throwable));
                }
            });
            addDisposable(disposable);
        }
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter {

        private List<LSStockDetail> mStockEntityList;

        public RecyclerViewAdapter(List<LSStockDetail> stockEntityList) {
            mStockEntityList = stockEntityList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stock, parent, false);
            RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
            return recyclerViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) holder;
            recyclerViewHolder.setItem(getItem(position));

            RxView.clicks(recyclerViewHolder.itemView)
                    .map(unit -> getItem(position)).subscribe(mMainPageViewModel.mInput.recyclerViewItemClick);
        }

        @Override
        public int getItemCount() {
            return mStockEntityList.size();
        }

        public LSStockDetail getItem(int position) {
            return mStockEntityList.get(position);
        }
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView mStockIDTextView;              //股票代號
        private TextView mStockNameTextView;            //股票名稱
        private TextView mTransVolumeTextView;          //買賣超
        private TextView mStockOpenPrizeTextView;       //開盤價
        private TextView mStockClosePrizeTextView;      //收盤價
        private TextView mStockRangeTextView;           //漲跌幅
        private ImageView mStockRangeStatusImageView;   //箭頭圖片

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mStockIDTextView = itemView.findViewById(R.id.stock_id_text_view);
            mStockNameTextView = itemView.findViewById(R.id.stock_name_text_view);
            mTransVolumeTextView = itemView.findViewById(R.id.over_number_text_view);
            mStockOpenPrizeTextView = itemView.findViewById(R.id.open_prize_text_view);
            mStockClosePrizeTextView = itemView.findViewById(R.id.close_prize_text_view);
            mStockRangeTextView = itemView.findViewById(R.id.range_text_view);
            mStockRangeStatusImageView = itemView.findViewById(R.id.range_image_view);
        }

        public void setItem(LSStockDetail item) {

            mStockIDTextView.setText(item.getStockID());
            mStockNameTextView.setText(item.getStockName());

            String totalOver = "0";
            if (item.getCorporationDetailList().size() > 0) {
                totalOver = item.getCorporationDetailList().get(item.getCorporationDetailList().size() - 1).getTotalOver();
            }
            mTransVolumeTextView.setText(totalOver);

            String openPrize = "";
            int openPrizeTextColor = R.color.white;
            String closePrize = "";
            int closePrizeTextColor = R.color.white;
            String range = "";
            int rangeImageViewID = R.drawable.ic_baseline_horizontal_rule_24;
            if (item.getStockRangeInfoDetailList().size() > 0) {
                LSStockRangeInfoDetail detail = item.getStockRangeInfoDetailList().get(item.getStockRangeInfoDetailList().size() - 1);
                openPrize = detail.getOpenPrize();
                closePrize = detail.getClosePrize();
                range = detail.getRange();

                switch (range.charAt(0)) {
                    case StockProperties.RangeStatus.UP: {
                        rangeImageViewID = R.drawable.ic_baseline_arrow_upward_24;
                        closePrizeTextColor = R.color.range_up;
                        break;
                    }
                    case StockProperties.RangeStatus.DOWN: {
                        rangeImageViewID = R.drawable.ic_baseline_arrow_downward_24;
                        closePrizeTextColor = R.color.range_down;
                        break;
                    }
                }

                double exPrizeDouble = Double.parseDouble(closePrize) - Double.parseDouble(range);
                detail.setExPrize(String.valueOf(exPrizeDouble));
                double openPrizeDouble = Double.parseDouble(openPrize);

                if (openPrizeDouble > exPrizeDouble) {
                    openPrizeTextColor = R.color.range_up;
                }

                if (openPrizeDouble < exPrizeDouble) {
                    openPrizeTextColor = R.color.range_down;
                }
            }

            mStockOpenPrizeTextView.setText(openPrize);
            mStockOpenPrizeTextView.setTextColor(getResources().getColor(openPrizeTextColor, null));
            mStockClosePrizeTextView.setText(closePrize);
            mStockClosePrizeTextView.setTextColor(getResources().getColor(closePrizeTextColor, null));
            mStockRangeTextView.setText(range);
            mStockRangeStatusImageView.setImageResource(rangeImageViewID);
        }
    }


}