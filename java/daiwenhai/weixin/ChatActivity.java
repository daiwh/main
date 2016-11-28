package daiwenhai.weixin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class ChatActivity extends AppCompatActivity {

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
                finish
        }
    }
}
