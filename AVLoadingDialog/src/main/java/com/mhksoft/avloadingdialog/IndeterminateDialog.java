package com.mhksoft.avloadingdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class IndeterminateDialog extends Dialog {
    private static final int DEFAULT_PADDING = 8;
    @NonNull
    private final Context mContext;
    @BindView(R2.id.title_tv)
    TextView titleTv;
    @BindView(R2.id.loading_avl)
    AVLoadingIndicatorView loadingAvl;
    @BindView(R2.id.description_tv)
    TextView descriptionTv;
    @BindView(R2.id.child_layout)
    LinearLayout childLayout;
    @BindView(R2.id.parent_layout)
    LinearLayout parentLayout;
    private PaintDrawable dialogBackground, titleBackground;
    private int cornerRadius;
    private int titleTextColor, descriptionTextColor, indicatorTintColor;
    private String title, description, indicatorName;
    private boolean rtl;

    public IndeterminateDialog(@NonNull Context context, String title, String description, String indicatorName, boolean cancelable) {
        super(context, cancelable, null);
        this.mContext = context;
        this.title = title;
        this.description = description;
        this.indicatorName = indicatorName;
        setDialogBackground("#ffffff", "#22313f", 8);
    }

    public void setDialogBackground(String dialogBackgroundColor, String titleBackgroundColor, int cornerRadius) {
        this.cornerRadius = cornerRadius;

        dialogBackground = new PaintDrawable();
        dialogBackground.setColorFilter(Color.parseColor(dialogBackgroundColor), PorterDuff.Mode.SRC_ATOP);
        dialogBackground.setCornerRadius(cornerRadius);

        titleBackground = new PaintDrawable();
        titleBackground.setColorFilter(Color.parseColor(titleBackgroundColor), PorterDuff.Mode.SRC_ATOP);
        titleBackground.setCornerRadii(new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0, 0, 0, 0});
    }

    public void setDialogBackground(@ColorRes int dialogBackgroundColor, @ColorRes int titleBackgroundColor, int cornerRadius) {
        this.cornerRadius = cornerRadius;

        dialogBackground = new PaintDrawable();
        dialogBackground.setColorFilter(mContext.getResources().getColor(dialogBackgroundColor), PorterDuff.Mode.SRC_ATOP);
        dialogBackground.setCornerRadius(cornerRadius);

        titleBackground = new PaintDrawable();
        titleBackground.setColorFilter(mContext.getResources().getColor(titleBackgroundColor), PorterDuff.Mode.SRC_ATOP);
        titleBackground.setCornerRadii(new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0, 0, 0, 0});
    }

    public void setStyle(String titleTextColor, String descriptionTextColor, String indicatorTintColor) {
        this.titleTextColor = Color.parseColor(titleTextColor);
        this.descriptionTextColor = Color.parseColor(descriptionTextColor);
        this.indicatorTintColor = Color.parseColor(indicatorTintColor);
    }

    public void setStyle(@ColorRes int titleTextColor, @ColorRes int descriptionTextColor, @ColorRes int indicatorTintColor) {
        this.titleTextColor = mContext.getResources().getColor(titleTextColor);
        this.descriptionTextColor = mContext.getResources().getColor(descriptionTextColor);
        this.indicatorTintColor = mContext.getResources().getColor(indicatorTintColor);
    }

    public void isRTL(boolean rtl) {
        this.rtl = rtl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.indeterminate_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ButterKnife.bind(this);

        init();
        initDialog();
        initStyle();
    }

    private void init() {
        if (title != null)
            titleTv.setText(title);
        else
            titleTv.setVisibility(View.GONE);

        if (description != null)
            descriptionTv.setText(description);
        else
            descriptionTv.setVisibility(View.GONE);

        loadingAvl.setIndicator(indicatorName);
    }

    private void initDialog() {
        int properPadding = DEFAULT_PADDING + cornerRadius;

        if (rtl)
            parentLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        else
            parentLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        parentLayout.setBackground(dialogBackground);
        childLayout.setPadding(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, properPadding);

        titleTv.setBackground(titleBackground);
        titleTv.setPadding(DEFAULT_PADDING, properPadding, DEFAULT_PADDING, DEFAULT_PADDING);
    }

    private void initStyle() {
        titleTv.setTextColor(titleTextColor);
        descriptionTv.setTextColor(descriptionTextColor);
        loadingAvl.setIndicatorColor(indicatorTintColor);
    }
}
