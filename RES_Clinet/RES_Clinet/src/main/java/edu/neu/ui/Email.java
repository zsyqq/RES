package edu.neu.ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import edu.neu.res_clinet.R;

public class Email extends TabActivity{
    private TabHost myTabhost;
    private EditText SendReceiver, SendTopic, SendKey, SendText, SearchKey, SearchSender;
    private Button SendReset, SendSend, SearchReset, SearchSearch;
    private String strSendReceiver, strSendTopic, strSendKey, strSendText, strSearchKey, strSearchSender;
    private final static String PrKPath = "/sdcard/darkblue/";
    private final static int countMax = 1000;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myTabhost = this.getTabHost();
        LayoutInflater.from(this).inflate(R.layout.message, myTabhost.getTabContentView(), true);
		
		/*
		 * 送信箱界面： 
		 */
        myTabhost.addTab(myTabhost.newTabSpec("One")
                .setIndicator("Inbox",getResources().getDrawable(R.drawable.email_inbox))
                .setContent(initInbox()));
		
		/*
		 * 发送邮件界面：
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Two")
                .setIndicator("Outbox",getResources().getDrawable(R.drawable.email_outbox))
                .setContent(R.id.Email_layout_outbox));
        // 实例化添加界面的控件。
        SendReceiver = (EditText) findViewById(R.id.ET_Email_SdReceiver);
        SendTopic = (EditText) findViewById(R.id.ET_Email_SdTopic);
        SendKey = (EditText) findViewById(R.id.ET_Email_SdKey);
        SendText = (EditText) findViewById(R.id.ET_Email_SdText);
        SendReset = (Button) findViewById(R.id.BU_Email_SdReset);
        SendSend = (Button) findViewById(R.id.BU_Email_SdSend);
        // 设置确定按钮。
        SendReset.setOnClickListener(new SendResetListener());
        SendSend.setOnClickListener(new SendSendListener());
		
		/*
		 * 搜索邮件界面：
		 */
        myTabhost.addTab(myTabhost.newTabSpec("Three")
                .setIndicator("Search",getResources().getDrawable(R.drawable.email_search))
                .setContent(R.id.Email_layout_search));
        // 实例化搜索界面的控件。
        SearchKey = (EditText) findViewById(R.id.ET_Email_ShKey);
        SearchSender = (EditText) findViewById(R.id.ET_Email_ShSender);
        SearchReset = (Button) findViewById(R.id.BU_Email_ShReset);
        SearchSearch = (Button) findViewById(R.id.BU_Email_ShSearch);
        // 设置确定按钮。
        SearchReset.setOnClickListener(new SearchResetListener());
        SearchSearch.setOnClickListener(new SearchSearchListener());
    }

    /*
     *  初始化收信箱。
     */
    public Intent initInbox(){
        Intent intent = new Intent(Email.this, EmailInbox.class);

        String t[] = new String[1];
        Bundle bundle = new Bundle();
        bundle.putInt("count", 0);
        bundle.putStringArray("msg", t);
        intent.putExtras(bundle);

        return intent;
    }

    /*
     *  响应重置信息按钮(outbox)单击事件：
     */
    class SendResetListener implements OnClickListener{
        public void onClick(View v) {
            SendReceiver.setText("");
            SendTopic.setText("");
            SendKey.setText("");
            SendText.setText("");
        }
    }

    /*
     *  响应发送按钮单击事件：
     */
    class SendSendListener implements OnClickListener{
        public void onClick(View v) {
            // 获取用户输入信息。
            strSendReceiver = SendReceiver.getText().toString().trim();
            strSendTopic = SendTopic.getText().toString().trim();
            strSendKey = SendKey.getText().toString().trim();
            strSendText = SendText.getText().toString().trim();

            if(strSendReceiver.equals("") || strSendKey.equals("")){
                Toast.makeText(Email.this, "请将发送的信息填写完整", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    /*
     *  响应重置信息按钮(search)单击事件：
     */
    class SearchResetListener implements OnClickListener{
        public void onClick(View v) {
            SearchKey.setText("");
            SearchSender.setText("");
        }
    }

    /*
     *  响应搜索按钮单击事件：
     */
    class SearchSearchListener implements OnClickListener{
        public void onClick(View v) {
            // 获取用户输入信息。
            strSearchKey = SearchKey.getText().toString().trim();
            strSearchSender = SearchSender.getText().toString().trim();
            if(strSearchKey.equals("") || strSearchSender.equals("")){
                Toast.makeText(Email.this, "请填写完整", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    /*
     *  提示信息msg。
     */
    private void showDialog(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}