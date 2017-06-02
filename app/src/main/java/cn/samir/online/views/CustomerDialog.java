package cn.samir.online.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.samir.online.R;
import cn.samir.online.util.Utils;


/**
 * Created by xiaw on 2015/9/22 0022.
 */
public class CustomerDialog implements View.OnClickListener {

    private Dialog mDialog;
    protected ViewGroup parentView;
    private Button.OnClickListener mPositiveListener = null;
    private Button.OnClickListener mNegativeListener = null;
    private CustomFontTextView btn_ensure;
    private CustomFontTextView btn_cancel;
    private TextView tv_title;
    private TextView tv_content;
    private Context context;

    public CustomerDialog(Context context, boolean isCancelable) {
        this.context = context;
        init();
        setCancelable(isCancelable);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CustomerDialog(Context context) {
        this(context, true);
    }


    public boolean isShow() {
        if (mDialog != null) {
            return mDialog.isShowing();
        }
        return false;
    }


    public static Dialog getInstance(Context context) {
        return new Dialog(context, R.style.Style_Dialog_right);
    }

    public CustomerDialog setCancelable(boolean b) {
        mDialog.setCanceledOnTouchOutside(b);
        mDialog.setCancelable(b);
        return this;
    }

    public void init() {
        mDialog = new Dialog(context, R.style.Style_Dialog_right);
        parentView = (ViewGroup) View.inflate(context, R.layout.dialog_message, null);
        mDialog.setContentView(parentView);
        tv_content = (TextView) parentView.findViewById(R.id.tv_content);
        tv_title = (TextView) parentView.findViewById(R.id.tv_title);
        btn_ensure = (CustomFontTextView) parentView.findViewById(R.id.btn_ensure);
        btn_cancel = (CustomFontTextView) parentView.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
        btn_ensure.setOnClickListener(this);
    }


    public CustomerDialog setTitle(String title) {
        tv_title.setText(title);
        return this;
    }

    public CustomerDialog setTitle(int title) {
        if (context != null)
            setTitle(context.getResources().getString(title));
        return this;
    }

    public CustomerDialog setMessage(int msg) {
        if (context != null)
            setMessage(context.getString(msg));
        return this;
    }


    public CustomerDialog setMessage(String msg) {
        tv_content.setText(msg);
        return this;
    }


    /**
     * 确定监听
     *
     * @param poListener
     */
    public CustomerDialog addPositiveListener(Button.OnClickListener poListener) {
        addPositiveListener(null, poListener);
        return this;
    }


    /**
     * 确定监听
     *
     * @param text       按钮显示文字
     * @param poListener
     */
    public CustomerDialog addPositiveListener(String text, Button.OnClickListener poListener) {
        this.mPositiveListener = poListener;
        if (!Utils.isEmpty(text)) {
            btn_ensure.setText(text);
        }
        btn_ensure.setOnClickListener(this);
        return this;
    }

    /**
     * 取消监听
     *
     * @param negativeListener
     */
    public CustomerDialog addNegativeListener(Button.OnClickListener negativeListener) {
        addNegativeListener(null, negativeListener);
        return this;
    }

    /**
     * 取消监听
     *
     * @param negativeListener
     */
    public CustomerDialog addNegativeListener(String text, Button.OnClickListener negativeListener) {
        this.mNegativeListener = negativeListener;
        if (!Utils.isEmpty(text)) {
            btn_cancel.setText(text);
        }
        btn_cancel.setOnClickListener(this);
        return this;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.btn_ensure:
                mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (mPositiveListener != null) {
                            mPositiveListener.onClick(v);
                        }
                    }
                });

                dismiss();

                break;
            case R.id.btn_cancel:

                mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (mNegativeListener != null) {
                            mNegativeListener.onClick(v);
                        }
                    }
                });


                dismiss();

                break;
        }
    }


    public CustomerDialog show() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
        return this;
    }

    public CustomerDialog dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }

        return this;
    }
}
