package daiwenhai.weixin;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

public class MainWeixin extends AppCompatActivity {
    public static MainWeixin instance = null;
    private ViewPager mTabPager;
    private ImageView mTabImg;                   //动画图片
    private ImageView mTab1, mTab2, mTab3, mTab4; //四个图片，微信，通讯录，朋友，设置
    private int zero = 0;                       //动画图片偏移量
    private int currindex = 1;                  //当前页卡编号
    private int one;                            //单个水平动画位移
    private int two;
    private int three;
    private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;
    private boolean menu_display = false;
    private PopupWindow menuWindow;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_weixin);
        //启动Activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        instance = this;
        mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
        mTab1 = (ImageView)findViewById(R.id.img_weixin);
        mTab2 = (ImageView)findViewById(R.id.img_address);
        mTab3 = (ImageView)findViewById(R.id.img_friends);
        mTab4 = (ImageView)findViewById(R.id.img_setting);

        mTabImg = (ImageView)findViewById(R.id.img_tab_now);

        //为四个图标添加监听器
        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));

        Display currDisplay = getWindowManager().getDefaultDisplay(); //获得当前屏幕分辨率
        int displayWidth = currDisplay.getWidth();
        int displayHeight = currDisplay.getHeight();
        one = displayWidth/4;      //设置水平动画平移大小
        two = one * 2;
        three = one * 3;
        Log.i("info", "获得屏幕的分辨率为"+one+two+three+"X"+displayHeight);

        //将要分页显示的View装入数组中
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.main_tab_weixin, null);
        View view2 = mLi.inflate(R.layout.main_tab_address, null);
        View view3 = mLi.inflate(R.layout.main_tab_friends, null);
        View view4 = mLi.inflate(R.layout.main_tab_settings, null);

        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        //填充ViewPager的数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            @Override
            public void destroyItem(View container, int position, Object object){
                ((ViewPager)container).removeView(views.get(position));
            }
            @Override
            public Object instantiateItem(View container, int position){
                ((ViewPager)container).addView(views.get(position));
                return views.get(position);
            }
        };
        mTabPager.setAdapter(mPagerAdapter);
    }
    /***************图标单击监听*******************/
    public class MyOnClickListener implements View.OnClickListener{
        private int index = 0;
        public MyOnClickListener(int index){
            this.index = index;
        }
        @Override
        public void onClick(View view){
            mTabPager.setCurrentItem(index);
        }
    }

    /***************页卡切换监听*******************/
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener{
        @Override
        public void onPageSelected(int index){
            Animation animation = null;
            switch (index){
                case 0:
                    mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
                    if(currindex ==1){
                        animation = new TranslateAnimation(one, 0, 0, 0);
                        mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
                    }else if(currindex == 2){
                        animation = new TranslateAnimation(two, 0, 0, 0);
                        mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
                    }else if(currindex ==3){
                        animation = new TranslateAnimation(three, 0, 0, 0);
                        mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
                    }
                    break;
                case 1:
                    mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
                    if(currindex == 0){
                        animation = new TranslateAnimation(zero, one, 0, 0);
                        mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
                    }else if(currindex == 2){
                        animation = new TranslateAnimation(two, one, 0, 0);
                        mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
                    }else if(currindex ==3){
                        animation = new TranslateAnimation(three, one, 0, 0);
                        mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
                    }
                    break;
                case 2:
                    mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
                    if(currindex ==0){
                        animation = new TranslateAnimation(zero, two, 0, 0);
                        mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
                    }else if(currindex == 1){
                        animation = new TranslateAnimation(one, two, 0, 0);
                        mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
                    }else if(currindex ==3){
                        animation = new TranslateAnimation(three, two, 0, 0);
                        mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
                    }
                    break;
                case 3:
                    mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
                    if(currindex == 0){
                    animation = new TranslateAnimation(zero, three, 0, 0);
                    mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_weixin_pressed));
                    } else if(currindex == 1){
                        animation = new TranslateAnimation(one, three, 0, 0);
                        mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
                    }else if(currindex == 2){
                        animation = new TranslateAnimation(two, three, 0, 0);
                        mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
                    }
                    break;
            }
            currindex = index;
            animation.setFillAfter(true);  //图片停在动画结束的位置
            animation.setDuration(150);    //图片停留时间
            mTabImg.startAnimation(animation);  //开始动画
        }
        @Override
        public void onPageScrolled(int a, float b, int c){
        }
        @Override
        public void onPageScrollStateChanged(int index){
        }
    }

    /****************键盘监听***************/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() ==0){ //如果返回键按下
            if(menu_display){   //如果菜单已打开，先关闭菜单
                menuWindow.dismiss();
                menu_display = false;
            }else{ //切换当前界面到退出界面
                Intent intent = new Intent();
                intent.setClass(MainWeixin.this, Exit.class);
                startActivity(intent);
            }
        }
        else if(keyCode == KeyEvent.KEYCODE_MENU){//如果菜单键按下
            if(!menu_display){ //如果菜单没有打开
                //获取LayoutInflater实例
                inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
                //这里的main布局是在inflate中加入的，以前是直接调用this.setContentView(),该方法返回的是view对象，是布局中的根
                layout = inflater.inflate(R.layout.main_menu, null);
                menuWindow = new PopupWindow(layout, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
                menuWindow.showAsDropDown(layout); //设置弹出效果
                menuWindow.showAsDropDown(null, 0, layout.getHeight());
                menuWindow.showAtLocation(this.findViewById(R.id.mainweixin), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);//设置layout在PopupWindow中的显示位置

                //获取main中的控件
                mClose = (LinearLayout)layout.findViewById(R.id.menu_close);
                mCloseBtn = (LinearLayout)layout.findViewById(R.id.menu_close_btn);

                mCloseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainWeixin.this, "退出", Toast.LENGTH_LONG).show();
                        Intent intent =new Intent();
                        intent.setClass(MainWeixin.this, Exit.class);
                        startActivity(intent);
                        menuWindow.dismiss();  //响应单击事件后关闭menu
                    }
                });
                menu_display = true;
        }else {//如果当前菜单那处于打开状态则关闭
                menuWindow.dismiss();
                menu_display = false;
            }
                return false;
            }
        return false;
    }

    //设置标题右侧按钮的作用
    public void btnmainright(View v){
        Intent intent = new Intent();
        intent.setClass(MainWeixin.this, MainTopRightDialog.class);
        startActivity(intent);
    }
    //对话界面
    public void startchat(View view){
        Intent intent =new Intent();
        intent.setClass(MainWeixin.this, ChatActivity.class);
        startActivity(intent);
    }
    //退出对话框
    public void exit_settings(View view){
        Intent intent =new Intent();
        intent.setClass(MainWeixin.this, ExitFromSettings.class);
        startActivity(intent);
    }
    public void btn_shake(View view){
        Intent intent = new Intent();
        intent.setClass(MainWeixin.this, ShakeActivity.class);
        startActivity(intent);
    }
}


