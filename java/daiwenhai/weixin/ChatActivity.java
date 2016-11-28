package daiwenhai.weixin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Calendar;


public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnSend;
    private Button mBtnBack;
    private EditText mEditTextContent;
    private ListView mListView;
    private ChatMsgViewAdapter mAdapter;
    private List<ChatMsgEntity> mDataArrays = new List<ChatMsgEntity>();

    private String[] msgArray = new String[]{"are you ok?","ok,ok","are you ok?","ok,ok","are you ok?","ok,ok","are you ok?","ok,ok"};
    private String[] dataArray = new String[]{"2016-11-27 18:00","2016-11-27 18:00",
                                              "2016-11-27 18:00","2016-11-27 18:00",
                                              "2016-11-27 18:00","2016-11-27 18:00",
                                              "2016-11-27 18:00","2016-11-27 18:00"};
    private final static int COUNT = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_xiaohei);
        //启动Activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initView();
        initData();
    }
    private void initView(){
        mListView = (ListView)findViewById(R.id.listview);
        mBtnSend = (Button)findViewById(R.id.btn_send);
        mBtnSend.setOnClickListener(this);
        mBtnBack = (Button)findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(this);

        mEditTextContent = (EditText)findViewById(R.id.et_sendmessage);
    }
    public void initData(){
        for(int i =0; i<COUNT ; i++){
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setData(dataArray[i]);
            if(i%2 ==0 ){
                entity.setName("小黑");
                entity.setMsgType(true);
            }else{
                entity.setName("我");
                entity.setMsgType(false);
            }
            entity.setText(msgArray[i]);
            mDataArrays.add(entity);
        }
        mAdapter = new ChatMsgViewAdapter(mAdapter);
    }

    @Override
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.btn_send:
                send();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
    private void send()
    {
        String contString = mEditTextContent.getText().toString();
        if(contString.length() > 0){
            ChatMsgEntity entity = new ChatMsgEntity();
            entity.setData(getData());
            entity.setName("我");
            entity.setMsgType(false);
            entity.setText(contString);

            mDataArrays.add(entity);
            mAdapter.notityDataSetChanged();

            mEditTextContent.setText("");

            mListView.setSelection(mListView.getCount()-1);
        }
    }

    private String getData()
    {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String day  = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)+1);
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minutes = String.valueOf(calendar.get(Calendar.MINUTE));

        StringBuffer time = new StringBuffer();
        time.append(year + "-" + month + "-" + day + " " + hour +":" + minutes);
        return time.toString();
    }

    public void head_xiaohei(View view)//标题栏返回按钮
    {
        Intent intent = new Intent();
        intent.setClass(ChatActivity.this, InfoXiaohei.class);
        startActivity(intent);
    }

}
