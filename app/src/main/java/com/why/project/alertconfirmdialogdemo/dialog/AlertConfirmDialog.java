package com.why.project.alertconfirmdialogdemo.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.why.project.alertconfirmdialogdemo.R;

/**
 * Created by HaiyuKing
 * Used 确定取消对话框
 */

public class AlertConfirmDialog extends AlertDialog{

	private Context context;//上下文

	private ImageView mIconImg;//图标
	private ScrollView mMsgScroll;//内容的父节点，用于内容较多时可以滚动
	private TextView mMessage;//内容
	private Button mConfirmBtn;//确认按钮
	private Button mCancelBtn;//取消按钮

	private int iconImgResId = 0;//图标的resid值
	private String mseeageStr = "";//内容文本
	private String confirmBtnTvStr = "";//确认按钮的文本
	private String cancelBtnTvStr = "";//取消按钮的文本
	private boolean cancelButtonHidden = false;//是否隐藏取消按钮

	public AlertConfirmDialog(Context context, int iconImgResId, String mseeageStr, String confirmBtnTvStr, String cancelBtnTvStr, boolean cancelButtonHidden) {
		super(context, R.style.style_alert_confirm_dialog);

		//设置为false，按对话框以外的地方不起作用
		setCanceledOnTouchOutside(false);
		//设置为false，按返回键不能退出
		setCancelable(true);

		this.context = context;
		this.iconImgResId = iconImgResId;
		this.mseeageStr = mseeageStr;
		this.confirmBtnTvStr = confirmBtnTvStr;
		this.cancelBtnTvStr = cancelBtnTvStr;
		this.cancelButtonHidden = cancelButtonHidden;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_dialog_confirm_cancel);

		initViews();
		initDatas();
		initEvents();
	}

	/**初始化view*/
	private void initViews() {
		mIconImg = (ImageView) findViewById(R.id.alertdialogconfirm_img);
		mMsgScroll = (ScrollView) findViewById(R.id.alertdialogconfirm_message_scroll);
		mMessage = (TextView) findViewById(R.id.alertdialogconfirm_message);
		mConfirmBtn = (Button) findViewById(R.id.alertdialogconfirm_confirm);
		mCancelBtn = (Button) findViewById(R.id.alertdialogconfirm_cancel);
	}

	/**初始化数据*/
	private void initDatas() {
		//赋值新的图标
		if(iconImgResId != 0){
			mIconImg.setBackgroundResource(iconImgResId);
		}
		//赋值内容文本
		mMessage.setText(mseeageStr);
		//赋值确认和取消按钮的文本
		if(!TextUtils.isEmpty(confirmBtnTvStr)){
			mConfirmBtn.setText(confirmBtnTvStr);
		}
		if(!TextUtils.isEmpty(cancelBtnTvStr)){
			mCancelBtn.setText(cancelBtnTvStr);
		}

		if(cancelButtonHidden){//隐藏取消按钮
			mCancelBtn.setVisibility(View.GONE);
			mConfirmBtn.setBackgroundResource(R.drawable.alert_dialog_confirm_one_btn_bg);
		}

		//设置内容文本居中对齐
		FrameLayout.LayoutParams msgParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		msgParams.gravity = Gravity.CENTER_HORIZONTAL;
		mMessage.setLayoutParams(msgParams);

		//设置内容区域的父节点的高度和内容文本大小
		final DisplayMetrics display = new DisplayMetrics();
		((Activity)this.context).getWindowManager().getDefaultDisplay().getMetrics(display);
		if (display.widthPixels <= 480) {
			mMessage.setTextSize(15.0F);
		}
		final int screenHeight = display.heightPixels;
//		Log.w(TAG,"display.widthPixels="+display.widthPixels);
//		Log.w(TAG,"screenHeight="+screenHeight);
		//runnable中的方法会在View的measure、layout等事件后触发
		mMsgScroll.post(new Runnable() {
			@Override
			public void run() {
//				Log.w(TAG,"mMsgScroll.getMeasuredHeight()="+mMsgScroll.getMeasuredHeight());
				if (mMsgScroll.getMeasuredHeight() > screenHeight / 2) {
					mMsgScroll.setLayoutParams(new LinearLayout.LayoutParams(display.widthPixels - context.getResources().getDimensionPixelOffset(R.dimen.alert_dialog_confirm_margin) * 2,
							screenHeight / 2));
				}else{
					//屏幕宽度减去外边距*2
					mMsgScroll.setLayoutParams(new LinearLayout.LayoutParams(display.widthPixels - context.getResources().getDimensionPixelOffset(R.dimen.alert_dialog_confirm_margin) * 2,
							ViewGroup.LayoutParams.WRAP_CONTENT));
				}
			}
		});
	}

	/**初始化监听事件*/
	private void initEvents() {
		//确定按钮的点击事件
		mConfirmBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(mOnCertainButtonClickListener != null){
					mOnCertainButtonClickListener.onCertainButtonClick();
				}
				dismiss();
			}
		});

		//取消按钮的点击事件
		mCancelBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(mOnCertainButtonClickListener != null){
					mOnCertainButtonClickListener.onCancleButtonClick();
				}
				dismiss();
			}
		});
	}

	public static abstract interface OnCertainButtonClickListener
	{
		//取消按钮的点击事件接口
		public abstract void onCancleButtonClick();
		//确认按钮的点击事件接口
		public abstract void onCertainButtonClick();
	}

	private OnCertainButtonClickListener mOnCertainButtonClickListener;

	public void setOnCertainButtonClickListener(OnCertainButtonClickListener mOnCertainButtonClickListener)
	{
		this.mOnCertainButtonClickListener = mOnCertainButtonClickListener;
	}
}
