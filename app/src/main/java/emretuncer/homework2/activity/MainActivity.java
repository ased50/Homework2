package emretuncer.homework2.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.jsoup.Jsoup;
import android.content.Context;
import android.widget.Toast;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import emretuncer.homework2.R;
import emretuncer.homework2.model.Announcement;
import emretuncer.homework2.model.News;

public class MainActivity extends AppCompatActivity {

    String foods = "";
    Thread thread;
    List<Announcement> announcementList = new ArrayList<>();
    List<News> newsList = new ArrayList<>();
    List<String> foodList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        if(internetKontrol()){
            progressDialog.setMessage("Bağlantı Kuruluyor");
            progressDialog.show();
        }else{
            progressDialog.dismiss();
            progressDialog.show();
        }
       // progressDialog.setMessage("Bağlantı Kuruluyor");
       // progressDialog.show();


        thread = new Thread(new Runnable() {
            @Override
            public void run() {



                Document doc1 = null;
                Document doc2 = null;
                try {
                    doc1 = Jsoup.connect("http://www.ybu.edu.tr/muhendislik/bilgisayar/").get();
                    doc2 = Jsoup.connect("http://ybu.edu.tr/sks/").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Elements newsHeadlines = doc1.select("div.cnContent").select("div.cncItem").select("a[href]");
                for (Element newsLink : newsHeadlines) {
                    News news = new News();
                    news.setLink("http://www.ybu.edu.tr/muhendislik/bilgisayar/"+newsLink.attr("href"));
                    news.setTitle(newsLink.text());
                    newsList.add(news);
                }

                Elements announcementHeadLines =  doc1.select("div.caContent").select("div.cncItem").select("a[href]");
                for (Element announceLink : announcementHeadLines) {
                    Announcement announcement = new Announcement();
                    announcement.setLink("http://www.ybu.edu.tr/muhendislik/bilgisayar/"+announceLink.attr("href"));
                    announcement.setTitle(announceLink.text());
                    announcementList.add(announcement);
                }

                Element foodTable = doc2.select("table").get(1);
                Elements foodRows = foodTable.select("td");
                for (int i = 1; i < foodRows.size(); i++) {
                    Element row = foodRows.get(i);
                    String foodText = row.text();
                    foodList.add(foodText);
                }
                progressDialog.dismiss();
                Intent intent  = new Intent(MainActivity.this, TabActivity.class);
                intent.putExtra("announcementList", (Serializable) announcementList);
                intent.putExtra("newsList", (Serializable) newsList);
                intent.putStringArrayListExtra("foodList",(ArrayList<String>) foodList);
                startActivity(intent);
            }
        });

        if(internetKontrol()){
            thread.start();
        }else{
            Toast.makeText(getApplicationContext(), "İnternet Yok!", Toast.LENGTH_LONG).show();
        }


    }

    protected boolean internetKontrol() {
        // TODO Auto-generated method stub
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

}
