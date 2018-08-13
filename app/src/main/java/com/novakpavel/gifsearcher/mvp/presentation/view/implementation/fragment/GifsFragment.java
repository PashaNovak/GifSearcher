package com.novakpavel.gifsearcher.mvp.presentation.view.implementation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.novakpavel.gifsearcher.R;
import com.novakpavel.gifsearcher.adapters.base.base_adapters.BasePaginationRecyclerViewAdapter;
import com.novakpavel.gifsearcher.adapters.gifs.GifsAdapter;
import com.novakpavel.gifsearcher.mvp.model.repository.dto.Datum;
import com.novakpavel.gifsearcher.mvp.presentation.presenter.GifsFragmentPresenter;
import com.novakpavel.gifsearcher.mvp.presentation.view.implementation.fragment.base.BasePaginationFragment;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.fragment.IGifsFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GifsFragment extends BasePaginationFragment<GifsFragmentPresenter> implements IGifsFragment{

    @InjectPresenter
    GifsFragmentPresenter gifsFragmentPresenter;

    private GifsAdapter gifsAdapter;

    @BindView(R.id.error_message)
    TextView error_message;

    @BindView(R.id.search_et)
    EditText searchEditText;

    @OnClick(R.id.search_btn)
    void search(){
        hideKeyboard();
        gifsFragmentPresenter.searchBtnClicked(searchEditText.getText().toString());
    }

    public static GifsFragment getInstance() {
        return new GifsFragment();
    }

    @Nullable
    @Override
    protected BasePaginationRecyclerViewAdapter getBasePaginationRecyclerViewAdapter() {
        return gifsAdapter;
    }

    @Override
    protected void initViewBeforePresenterAttach() {
        super.initViewBeforePresenterAttach();
    }

    @Override
    protected void setPaginationRecyclerAdapter() {
        gifsAdapter = new GifsAdapter(null, null);
        recyclerView.setAdapter(gifsAdapter);
    }

    @Override
    protected void setLayoutManager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public int getLayoutResId() {
        return R.layout.layout_gifs_fragment;
    }

    @Override
    protected GifsFragmentPresenter getPresenter() {
        return gifsFragmentPresenter;
    }

    @Override
    public void showNoGifsMessage() {
        error_message.setVisibility(View.VISIBLE);
    }

    @Override
    public void skipNoGifsMessage() {
        error_message.setVisibility(View.GONE);
    }

    @Override
    public void addData(List<Datum> data) {
        gifsAdapter.addData(data);
    }
}
