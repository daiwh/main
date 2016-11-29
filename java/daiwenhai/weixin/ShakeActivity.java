package daiwenhai.weixin;

import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.Toast;

public class ShakeActivity extends AppCompatActivity {
    ShakeListener mShakeListener = null;
    Vibrator mVibrator;
    private RelativeLayout mImgUp;
    private RelativeLayout mImgDown;
    private RelativeLayout mTitle;

    private SlidingDrawer mDrawer;
    private Button mDrawerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shake_activity);

       // drawerSet(); //设置drawer监听，切换按钮的方向
        mVibrator = (Vibrator) getApplication().getSystemService(VIBRATOR_SERVICE);
        mImgUp = (RelativeLayout) findViewById(R.id.shakeImgUp);
        mImgDown = (RelativeLayout) findViewById(R.id.shakeImgDown);
        mTitle = (RelativeLayout) findViewById(R.id.shake_title_bar);
        mDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
        mDrawerBtn = (Button) findViewById(R.id.handle);
        mDrawerBtn.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED: //设定SlidingDrawer开启时的事件处理
                        mDrawerBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shake_report_dragger_down));
                        TranslateAnimation titleUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_PARENT,
                                0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -1.0f);
                        titleUp.setDuration(200);
                        titleUp.setFillAfter(true);
                        mTitle.startAnimation(titleUp);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        mDrawerBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shake_report_dragger_up));
                        TranslateAnimation titleDown = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF,
                                0f, Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0f);
                        titleDown.setDuration(200);
                        titleDown.setFillAfter(false);
                        mTitle.startAnimation(titleDown);
                        break;
                }
                return false;
            }
        });
        mShakeListener = new ShakeListener(this);
        mShakeListener.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                startAnim();//开始摇一摇
                mShakeListener.stop();
                startVibrato(); //开始震动
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast mToast;
                        mToast = Toast.makeText(getApplicationContext(), "抱歉，没有找到在同一时刻摇一摇的用户", Toast.LENGTH_SHORT);
                        mToast.show();
                        mVibrator.cancel();
                        mShakeListener.start();
                    }
                }, 2000);
            }
        });
    }
        //定义摇一摇动画
        public void startAnim() {
            AnimationSet animup = new AnimationSet(true);
            TranslateAnimation mytranslateanimup0 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -0.5f);
            mytranslateanimup0.setDuration(1000);
            TranslateAnimation mytranslateanimup1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, +0.5f);
            mytranslateanimup1.setDuration(1000);
            mytranslateanimup1.setStartOffset(1000);
            animup.addAnimation(mytranslateanimup0);
            animup.addAnimation(mytranslateanimup1);
            mImgUp.startAnimation(animup);

            AnimationSet animdown = new AnimationSet(true);
            TranslateAnimation mytranslateanimdown0 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, +0.5f);
            mytranslateanimdown0.setDuration(1000);
            TranslateAnimation mytranslateanimdown1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                    Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -0.5f);
            mytranslateanimdown1.setDuration(1000);
            mytranslateanimdown1.setStartOffset(1000);
            animdown.addAnimation(mytranslateanimdown0);
            animdown.addAnimation(mytranslateanimdown1);
            mImgDown.startAnimation(animdown);
        }

    //定义震动
    public void startVibrato(){
        mVibrator.vibrate(new long[]{500, 200, 500, 200}, -1);//数组中为节奏， 最后一个参数为重复次数， -1表示不重复， 非-1表示从指定的下标开始重复
    }

    //标题栏返回按钮
    public void shake_activity_back(View view){
        this.finish();
    }
    //标题栏
    public void linshi(View view){
        startAnim();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mShakeListener!=null){
            mShakeListener.stop();
        }
    }
}

