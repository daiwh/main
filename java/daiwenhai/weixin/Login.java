package daiwenhai.weixin;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText mUser;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mUser = (EditText)findViewById(R.id.login_user_edit);
        mPassword = (EditText)findViewById(R.id.login_password_edit);
    }

    /*登陆事件*/
    public void login_mainweixin(View view){
        if(mUser.getText().toString().equals("daidia") && mPassword.getText().toString().equals("daidai")){
            Intent intent = new Intent();
            intent.setClass(Login.this, LoadingActivity.class);
            startActivity(intent);
        }else {
             new AlertDialog.Builder(Login.this)
            .setIcon(getResources().getDrawable(R.drawable.login_error_icon))
             .setTitle("登陆失败")
             .setMessage("账号或密码输入不正确")
             .create().show();
        }
    }
    /*标题栏返回按钮*/
    public void login_back(View view){
        this.finish();
    }
    /*忘记密码按钮*/
    public void login_pw(View view){
        Uri uri = Uri.parse("http://3g.qq.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
