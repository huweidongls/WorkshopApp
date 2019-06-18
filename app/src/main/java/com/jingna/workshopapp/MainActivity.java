package com.jingna.workshopapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.workshopapp.app.MyApplication;
import com.jingna.workshopapp.fragment.FragmentEntrust;
import com.jingna.workshopapp.fragment.FragmentIndex;
import com.jingna.workshopapp.fragment.FragmentMy;
import com.jingna.workshopapp.fragment.FragmentRaise;
import com.jingna.workshopapp.fragment.FragmentStory;
import com.jingna.workshopapp.util.ToastUtil;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    @BindView(R.id.menu_index)
    ImageButton ibIndex;
    @BindView(R.id.menu_entrust)
    ImageButton ibEntrust;
    @BindView(R.id.menu_story)
    ImageButton ibStory;
    @BindView(R.id.menu_raise)
    ImageButton ibRaise;
    @BindView(R.id.menu_my)
    ImageButton ibMy;
    @BindView(R.id.menu1)
    RelativeLayout rl1;
    @BindView(R.id.menu2)
    RelativeLayout rl2;
    @BindView(R.id.menu3)
    RelativeLayout rl3;
    @BindView(R.id.menu4)
    RelativeLayout rl4;
    @BindView(R.id.menu5)
    RelativeLayout rl5;
    @BindView(R.id.tv_index)
    TextView tvIndex;
    @BindView(R.id.tv_entrust)
    TextView tvEntrust;
    @BindView(R.id.tv_story)
    TextView tvStory;
    @BindView(R.id.tv_raise)
    TextView tvRaise;
    @BindView(R.id.tv_my)
    TextView tvMy;

    private long exitTime = 0;

    private List<Fragment> fragmentList = new ArrayList<>();
    private MenuOnClickListener listener = new MenuOnClickListener();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);
        MyApplication.getInstance().addActivity(this);
        init();

        PermissionManager.instance().request(this, new OnPermissionCallback() {
                    @Override
                    public void onRequestAllow(String permissionName) {
//                DialogUtil.showTips(mContext, getString(R.string.permission_control),
//                        getString(R.string.permission_allow) + "\n" + permissionName);
                        Log.e("123123", "1"+permissionName);
                    }

                    @Override
                    public void onRequestRefuse(String permissionName) {
//                DialogUtil.showTips(mContext, getString(R.string.permission_control),
//                        getString(R.string.permission_refuse) + "\n" + permissionName);
                        Log.e("123123", "2"+permissionName);
                    }

                    @Override
                    public void onRequestNoAsk(String permissionName) {
//                DialogUtil.showTips(mContext, getString(R.string.permission_control),
//                        getString(R.string.permission_noAsk) + "\n" + permissionName);
                        Log.e("123123", "3");
                    }
                }, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE);

    }

    /**
     * 初始化各个组件
     */
    private void init() {

        ibIndex.setOnClickListener(listener);
        ibEntrust.setOnClickListener(listener);
        ibStory.setOnClickListener(listener);
        ibRaise.setOnClickListener(listener);
        ibMy.setOnClickListener(listener);

        rl1.setOnClickListener(listener);
        rl2.setOnClickListener(listener);
        rl3.setOnClickListener(listener);
        rl4.setOnClickListener(listener);
        rl5.setOnClickListener(listener);
        Fragment fragmentIndex = new FragmentIndex();
        Fragment fragmentEntrust = new FragmentEntrust();
        Fragment fragmentStory = new FragmentStory();
        Fragment fragmentRaise = new FragmentRaise();
        Fragment fragmentMy = new FragmentMy();

        fragmentList.add(fragmentIndex);
        fragmentList.add(fragmentEntrust);
        fragmentList.add(fragmentStory);
        fragmentList.add(fragmentRaise);
        fragmentList.add(fragmentMy);

        fragmentTransaction.add(R.id.fl_container, fragmentIndex);
        fragmentTransaction.add(R.id.fl_container, fragmentEntrust);
        fragmentTransaction.add(R.id.fl_container, fragmentStory);
        fragmentTransaction.add(R.id.fl_container, fragmentRaise);
        fragmentTransaction.add(R.id.fl_container, fragmentMy);

        fragmentTransaction.show(fragmentIndex).hide(fragmentEntrust).hide(fragmentStory).hide(fragmentRaise).hide(fragmentMy);
        fragmentTransaction.commit();

        selectButton(ibIndex);
        selectText(tvIndex);

    }

    private class MenuOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.menu_index:
                    selectButton(ibIndex);
                    selectText(tvIndex);
                    switchFragment(0);
                    break;
                case R.id.menu_entrust:
                    selectButton(ibEntrust);
                    selectText(tvEntrust);
                    switchFragment(1);
                    break;
                case R.id.menu_story:
                    selectButton(ibStory);
                    selectText(tvStory);
                    switchFragment(2);
                    break;
                case R.id.menu_raise:
                    selectButton(ibRaise);
                    selectText(tvRaise);
                    switchFragment(3);
                    break;
                case R.id.menu_my:
                    selectButton(ibMy);
                    selectText(tvMy);
                    switchFragment(4);
                    break;
                case R.id.menu1:
                    selectButton(ibIndex);
                    selectText(tvIndex);
                    switchFragment(0);
                    break;
                case R.id.menu2:
                    selectButton(ibEntrust);
                    selectText(tvEntrust);
                    switchFragment(1);
                    break;
                case R.id.menu3:
                    selectButton(ibStory);
                    selectText(tvStory);
                    switchFragment(2);
                    break;
                case R.id.menu4:
                    selectButton(ibRaise);
                    selectText(tvRaise);
                    switchFragment(3);
                    break;
                case R.id.menu5:
                    selectButton(ibMy);
                    selectText(tvMy);
                    switchFragment(4);
                    break;
            }

        }
    }

    /**
     * 选择隐藏与显示的Fragment
     *
     * @param index 显示的Frgament的角标
     */
    public void switchFragment(int index) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        for (int i = 0; i < fragmentList.size(); i++) {
            if (index == i) {
                fragmentTransaction.show(fragmentList.get(index));
            } else {
                fragmentTransaction.hide(fragmentList.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    public void selectText(View v) {
        tvIndex.setSelected(false);
        tvEntrust.setSelected(false);
        tvStory.setSelected(false);
        tvRaise.setSelected(false);
        tvMy.setSelected(false);
        v.setSelected(true);
    }

    /**
     * 控制底部菜单按钮的选中
     *
     * @param v
     */
    public void selectButton(View v) {
        ibIndex.setSelected(false);
        ibEntrust.setSelected(false);
        ibStory.setSelected(false);
        ibRaise.setSelected(false);
        ibMy.setSelected(false);
        v.setSelected(true);
    }

    @Override
    public void onBackPressed() {
        backtrack();
    }

    /**
     * 退出销毁所有activity
     */
    private void backtrack() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtil.showShort(context, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
            exitTime = 0;
        }
    }

}
