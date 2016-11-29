package daiwenhai.weixin;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Appsrart extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appsrart);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        Toast.makeText(getApplicationContext(), "孩子，好好背诵", Toast.LENGTH_LONG).show();
       // overridePendingTransition(R.anim.hyperspace_in, R.anim.hyperspace_out);
     /*   new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Appsrart.this, Welcome.class);
                startActivity(intent);
                Appsrart.this.finish();
            }
        }, 1000);*/
    }
}
