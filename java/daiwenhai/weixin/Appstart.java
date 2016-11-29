package daiwenhai.weixin;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Appstart extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //去掉标题栏,必须要放在setcontentView方法的前边
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.appstart);
        Toast.makeText(getApplicationContext(), "欢迎使用此版本的微信", Toast.LENGTH_LONG).show();
       // overridePendingTransition(R.anim.hyperspace_in, R.anim.hyperspace_out);//第一个参数为第一个activity进入时的动画，第二个人参数为第二个activity退出时的动画
        //通过handler的postDelayed实现1000ms延迟过度
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Appstart.this, Login.class);
                startActivity(intent);
                Appstart.this.finish();
            }
        }, 1000);
    }
}
