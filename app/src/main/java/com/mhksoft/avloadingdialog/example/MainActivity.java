package com.mhksoft.avloadingdialog.example;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mhksoft.avloadingdialog.IndeterminateDialog;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title_et)
    EditText titleEt;
    @BindView(R.id.description_tv)
    EditText descriptionTv;
    @BindView(R.id.indicatorName_tv)
    EditText indicatorNameTv;
    @BindView(R.id.rtl_cb)
    CheckBox rtlCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.action_btn)
    public void onViewClicked() {
        IndeterminateDialog dialog = new IndeterminateDialog(this,
                titleEt.getText().toString(),
                descriptionTv.getText().toString(),
                indicatorNameTv.getText().toString(),
                true);
        dialog.setDialogBackground("#FFFFFF", "#FF0000", 4);
        dialog.setStyle("#FFFFFF", "#000000", "#000000");
        dialog.isRTL(rtlCb.isChecked());
        dialog.show();
    }
}
