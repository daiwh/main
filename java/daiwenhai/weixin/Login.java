package daiwenhai.weixin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    private EditText mUser;
    private EditText mPassword;

    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mUser = (EditText)findViewById(R.id.login_user_edit);
        mPassword = (EditText)findViewById(R.id.login_password_edit);
    }

    /*登陆事件*/
    public void login_mainweixin(View view){
        db = new DB();
        if(true){       /*db.login(mUser.getText().toString(), mPassword.getText().toString())*/
            UserMessage.userName = mUser.getText().toString();
            Intent intent = new Intent();
            intent.setClass(Login.this, LoadingActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(Login.this, "账号或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
            mUser.setText("");
            mPassword.setText("");
        }
    }
    /*标题栏返回按钮*/
    public void login_back(View view){
        this.finish();
    }
    /*忘记密码按钮*/
    public void login_pw(View view){
        Uri uri = Uri.parse("http://123.207.124.135:8080/personweb/personweb/loginin.jsp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
