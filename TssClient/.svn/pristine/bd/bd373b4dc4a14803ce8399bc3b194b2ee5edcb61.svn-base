package com.icecream.tssclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.icecream.tssclient.util.ExitApplication;
import com.icecream.tssclient.util.Util;

public class LoginActivity extends Activity {
	private TextView activitynameTextView;
	private EditText usernameEditText;
	private EditText passwordEditText;
	private CheckBox rememberpasswordCheckBox;
	private Button loginButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		activitynameTextView = (TextView) findViewById(R.id.activitytitle);
		usernameEditText = (EditText) findViewById(R.id.UserNameEditText);
		passwordEditText = (EditText) findViewById(R.id.PasswordEditText);
		rememberpasswordCheckBox = (CheckBox) findViewById(R.id.rememberpassword);
		loginButton = (Button) findViewById(R.id.LoginButton);

		rememberpasswordCheckBox.setChecked(Util.remember);
		activitynameTextView.setText("PLEASE LOGIN");
		usernameEditText.setText(Util.userName);
		passwordEditText.setText(Util.password);
		rememberpasswordCheckBox.setOnCheckedChangeListener(rememberListener);
		loginButton.setOnClickListener(loginListener);

		ExitApplication.getInstance().addActivity(this);

	}

	public OnClickListener loginListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Util.userName = usernameEditText.getText().toString();
			Util.password = passwordEditText.getText().toString();
			Intent intent = new Intent(LoginActivity.this,
					LoginingActivity.class);
			startActivity(intent);
		}
	};

	public OnCheckedChangeListener rememberListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
			if (rememberpasswordCheckBox.isChecked()) {
				Util.remember = true;
			} else {
				Util.remember = false;
			}
		}
	};

}