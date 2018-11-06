package com.czq.module_main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.czq.module_common.log.LoggerTool;
import com.czq.module_common.tool.router.MyARouter;
import com.czq.module_common.tool.router.RouterTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path=MyARouter.MainActivity)
public class MainActivity extends AppCompatActivity {


    @BindView(R2.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ButterKnife.bind(this);
    }


    @OnClick(R2.id.button)
    public void onViewClicked() {
        LoggerTool.logdebug("点击");
        RouterTool.getInstance(getApplication()).build(MyARouter.LoginActivity).navigation(this);
    }
}
