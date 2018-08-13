package com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.fragment;

import com.novakpavel.gifsearcher.mvp.model.repository.dto.Datum;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.base.IAddListData;
import com.novakpavel.gifsearcher.mvp.presentation.view.interfaces.base.IBasePaginationView;

public interface IGifsFragment extends IBasePaginationView, IAddListData<Datum> {

    void showNoGifsMessage();

    void skipNoGifsMessage();
}
