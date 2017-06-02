package cn.samir.online.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.meituan.robust.PatchExecutor;
import com.meituan.robust.patch.annotaion.Modify;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.samir.online.R;
import cn.samir.online.patchs.PatchCallBack;
import cn.samir.online.patchs.PatchManipulateImp;

public class TestActivity extends Activity {

    @BindView(R.id.btn_patch)
    Button btnPatch;
    @BindView(R.id.btn_show)
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }


    private void runRobust() {
        new PatchExecutor(getApplicationContext(), new PatchManipulateImp(), new PatchCallBack()).start();
    }


    @Modify

    @OnClick({R.id.btn_patch, R.id.btn_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_patch:
                runRobust();
                break;
            case R.id.btn_show:
                Toast.makeText(this, "修改之后的222222222222222222222222222", Toast.LENGTH_LONG).show();
                //Toast.makeText(this, "修改之前的", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
