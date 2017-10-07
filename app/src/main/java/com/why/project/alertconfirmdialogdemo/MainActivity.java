package com.why.project.alertconfirmdialogdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.why.project.alertconfirmdialogdemo.dialog.AlertConfirmDialog;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";

	private Button btn_showdialog1;
	private Button btn_showdialog2;
	private Button btn_showdialog3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initViews();
		initEvents();
	}

	private void initViews() {
		btn_showdialog1 = (Button) findViewById(R.id.btn_showdialog1);
		btn_showdialog2 = (Button) findViewById(R.id.btn_showdialog2);
		btn_showdialog3 = (Button) findViewById(R.id.btn_showdialog3);
	}

	private void initEvents() {
		btn_showdialog1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertConfirmDialog confirmAlertDialog = new AlertConfirmDialog(MainActivity.this,
						0,"确定重新登录？","重新登录","放弃登录",false);
				confirmAlertDialog.setOnCertainButtonClickListener(new AlertConfirmDialog.OnCertainButtonClickListener() {
					@Override
					public void onCancleButtonClick() {
						Toast.makeText(MainActivity.this,"放弃登录",Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onCertainButtonClick() {
						Toast.makeText(MainActivity.this,"重新登录",Toast.LENGTH_SHORT).show();
					}
				});
				confirmAlertDialog.show();
			}
		});

		btn_showdialog2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertConfirmDialog confirmAlertDialog = new AlertConfirmDialog(MainActivity.this,
						0,"请检查用户名","","",true);
				confirmAlertDialog.setOnCertainButtonClickListener(new AlertConfirmDialog.OnCertainButtonClickListener() {
					@Override
					public void onCancleButtonClick() {
					}

					@Override
					public void onCertainButtonClick() {
						Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
					}
				});
				confirmAlertDialog.show();
			}
		});

		btn_showdialog3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertConfirmDialog confirmAlertDialog = new AlertConfirmDialog(MainActivity.this,
						0,"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行" +
						"此处内容超过一行此处内容超过一行此处内容超过一行","","",false);
				confirmAlertDialog.setOnCertainButtonClickListener(new AlertConfirmDialog.OnCertainButtonClickListener() {
					@Override
					public void onCancleButtonClick() {
						Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onCertainButtonClick() {
						Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
					}
				});
				confirmAlertDialog.show();
			}
		});
	}
}
