import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class hunt {

    protected static HttpResponse doPost(String mURL, String json) throws java.io.IOException {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(mURL);
        post.addHeader("userId","B1faATqCz");

        post.addHeader("Content-Type","application/json");

        HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
        post.setEntity(entity);
        return client.execute(post);

    }

    protected static HttpResponse doGet(String url) throws java.io.IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        get.addHeader("userId","B1faATqCz");
        return client.execute(get);
    }

    /*public void run() {
        String entity = "";

        try {
            HttpResponse response =
                    (mMethod == RemoteHandler.GET) ?
                            doGet() :
                            doPost();

            entity = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            android.util.Log.e("RemoteHandler", "An error occoured");
            e.printStackTrace();
        }
    }*/


    public static void main(String[] args) throws IOException, ParseException {

        String getStr="http://http-hunt.thoughtworks-labs.net/challenge/input";
        String postStr="http://http-hunt.thoughtworks-labs.net/challenge/output";


        /*
        Challenge:1

        HttpResponse hr= doGet(getStr);
        String val=EntityUtils.toString(hr.getEntity());

        System.out.println(val);
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList ar =objectMapper.readValue(val, ArrayList.class);

        System.out.println(ar.size());

        Map<String,Integer> map = new HashMap<>();
        map.put("count",ar.size());




        op opt= new op();

        opt.output=map;
        System.out.println(objectMapper.writeValueAsString(opt));
        hr=doPost(postStr,objectMapper.writeValueAsString(opt));
        val=EntityUtils.toString(hr.getEntity());



        System.out.println(val);*/


        //Challenge:2

        /*HttpResponse hr= doGet(getStr);
        String val=EntityUtils.toString(hr.getEntity());

        System.out.println(val);
        ObjectMapper objectMapper = new ObjectMapper();

        Items[] ar =objectMapper.readValue(val, Items[].class);

        System.out.println(ar.length);

        int count=0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(sdf.format(new Date()));

        for(Items it: ar){
            if(dt.getTime()>=it.startDate.getTime() && it.endDate==null){
                count++;
            }
            else {

                if (dt.getTime()>=it.startDate.getTime() && dt.getTime()<=it.endDate.getTime()) {
                    count ++;
                }
            }

        }

        Map<String,Integer> map = new HashMap<>();
        map.put("count",count);




        op opt= new op();

        opt.output=map;
        System.out.println(objectMapper.writeValueAsString(opt));
        hr=doPost(postStr,objectMapper.writeValueAsString(opt));
        val=EntityUtils.toString(hr.getEntity());



        System.out.println(val);
        */


        //challenge 3

        /*HttpResponse hr= doGet(getStr);
        String val=EntityUtils.toString(hr.getEntity());

        System.out.println(val);
        ObjectMapper objectMapper = new ObjectMapper();

        Items[] ar =objectMapper.readValue(val, Items[].class);

        System.out.println(ar.length);

        int count=0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(sdf.format(new Date()));

        Map<String,Integer> map = new HashMap<>();



        for(Items it: ar){
            if((dt.getTime()>=it.startDate.getTime() && it.endDate==null) || dt.getTime()>=it.startDate.getTime() && dt.getTime()<=it.endDate.getTime()){

                Integer c =map.get(it.category);
                if(c==null){
                    map.put(it.category,1);
                } else {
                    map.put(it.category,++c);
                }

            }
        }





        op opt= new op();

        opt.output=map;
        System.out.println(objectMapper.writeValueAsString(opt));
        hr=doPost(postStr,objectMapper.writeValueAsString(opt));
        val=EntityUtils.toString(hr.getEntity());



        System.out.println(val);*/

        //Challenge 4


        HttpResponse hr= doGet(getStr);
        String val= EntityUtils.toString(hr.getEntity());

        System.out.println(val);
        ObjectMapper objectMapper = new ObjectMapper();

        Items[] ar =objectMapper.readValue(val, Items[].class);

        System.out.println(ar.length);

        int value=0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(sdf.format(new Date()));

        Map<String,Integer> map = new HashMap<>();



        for(Items it: ar){
            if((dt.getTime()>=it.startDate.getTime() && it.endDate==null) || dt.getTime()>=it.startDate.getTime() && dt.getTime()<=it.endDate.getTime()){

                value+=it.price;

            }
        }

        map.put("totalValue",value);

        op opt= new op();

        opt.output=map;
        System.out.println(objectMapper.writeValueAsString(opt));
        hr=doPost(postStr,objectMapper.writeValueAsString(opt));
        val=EntityUtils.toString(hr.getEntity());

        System.out.println(val);

    }



}

class Items implements Serializable {
    public String name;
    public String category;
    public Integer price;
    public Date startDate;
    public Date endDate;
}

class op implements Serializable  {
    public Map<String,Integer> output;
}