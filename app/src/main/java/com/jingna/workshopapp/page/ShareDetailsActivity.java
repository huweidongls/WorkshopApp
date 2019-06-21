package com.jingna.workshopapp.page;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jingna.workshopapp.R;
import com.jingna.workshopapp.base.BaseActivity;
import com.jingna.workshopapp.util.StatusBarUtils;
import com.jingna.workshopapp.widget.ObservableScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareDetailsActivity extends BaseActivity {

    private Context context = ShareDetailsActivity.this;

    @BindView(R.id.scrollView)
    ObservableScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_details);

        StatusBarUtils.setStatusBarTransparent(ShareDetailsActivity.this);
        ButterKnife.bind(ShareDetailsActivity.this);
        initView();

    }

    private void initView() {

//        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
//            @Override
//            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
//                if (y <= 0) {
//                    rlSearch.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
////                    StatusBarUtils.setStatusBarTransparent(getActivity());
//                    llSearch.setBackgroundResource(R.drawable.bg_index_search);
//                    rlSaoyisao.setBackgroundResource(R.drawable.bg_42000000_round);
//                    rlMessage.setBackgroundResource(R.drawable.bg_42000000_round);
//                    Glide.with(getContext()).load(R.mipmap.saoyisao).into(ivSaoyisao);
//                    Glide.with(getContext()).load(R.mipmap.message).into(ivMessage);
//                    Glide.with(getContext()).load(R.mipmap.search).into(ivSmallSearch);
//                    tvSearchText.setTextColor(Color.parseColor("#9C9C9C"));
//                } else if (y > 0 && y <= 888) {
//                    float scale = (float) y / 888;
//                    float alpha = (255 * scale);
//                    // 只是layout背景透明
//                    rlSearch.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
////                    StatusBarUtils.setStatusBar(getActivity(), Color.argb((int) alpha, 255, 255, 255));
//                } else {
//                    rlSearch.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
////                    StatusBarUtils.setStatusBar(getActivity(), Color.argb((int) 255, 255, 255, 255));
//                    llSearch.setBackgroundResource(R.drawable.bg_index_search_dark);
//                    rlSaoyisao.setBackgroundResource(R.drawable.bg_ffffff_round);
//                    rlMessage.setBackgroundResource(R.drawable.bg_ffffff_round);
//                    Glide.with(getContext()).load(R.mipmap.saoyisao_dark).into(ivSaoyisao);
//                    Glide.with(getContext()).load(R.mipmap.message_dark).into(ivMessage);
//                    Glide.with(getContext()).load(R.mipmap.search_light).into(ivSmallSearch);
//                    tvSearchText.setTextColor(Color.parseColor("#ffffff"));
//                }
//            }
//        });

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
