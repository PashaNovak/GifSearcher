package com.novakpavel.gifsearcher.adapters.gifs;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.novakpavel.gifsearcher.R;
import com.novakpavel.gifsearcher.adapters.base.base_view_holders.BaseViewHolder;
import com.novakpavel.gifsearcher.mvp.model.repository.dto.Datum;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.EncodeStrategy.SOURCE;

public class GifsViewHolder extends BaseViewHolder<Datum> {

    @BindView(R.id.gif_container)
    ImageView gifContainer;

    @BindView(R.id.gif_source)
    TextView gifSource;

    @BindView(R.id.gif_username)
    TextView username;

    public GifsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(@Nullable Datum datum) {
        super.bindView(datum);
        if (datum != null) {

            username.setText(datum.getUsername());

            RequestOptions requestOptions = new RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(Integer.parseInt(datum.getImages().getFixedWidth().getWidth()),
                            Integer.parseInt(datum.getImages().getFixedWidth().getHeight()))
                    .fitCenter();

            Glide.with(itemView.getContext())
                    .asGif()
                    .load(datum.getImages().getFixedWidth().getUrl())
                    .apply(requestOptions)
                    .into(gifContainer);
            gifSource.setText(datum.getSource());
        }
    }
}
