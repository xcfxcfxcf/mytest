package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class testUrl {
	//Ӧ��ID
	private static String agent_id = "10030";
	//Ӧ����Կ
	private static String agent_secret = "2m12NmfeIQatc2QJjG7dqeL3FEKtieyV";
	//����http��ַ
	private static String httpPost = "http://qixin.osnyun.com:9090";
	
	public static void main(String[] args) {
		String result = "";  
        BufferedReader in = null;  
        try {  
            String urlName = "";  
            urlName = "http://qixin.osnyun.com:9090/eim/api/get_token?agent_id=10030&agent_secret=2m12NmfeIQatc2QJjG7dqeL3FEKtieyV";
            //Log.i("ConnUtil", urlName);  
            System.out.println(urlName);  
            URL realUrl = new URL(urlName);  
            // �򿪺�URL֮�������  
            URLConnection conn = realUrl.openConnection();  
            // ����ʵ�ʵ�����  
            conn.connect();  
            // ��ȡ������Ӧͷ�ֶ�  
            Map< String,List< String>> map = conn.getHeaderFields();  
            // �������е���Ӧͷ�ֶ�  
            for (String key : map.keySet()) {  
                System.out.println(key + "--->" + map.get(key));  
            }  
            // ����BufferedReader����������ȡURL����Ӧ  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += "\n" + line;  
            }  
            JSONObject jsStr = JSONObject.fromObject(result); //���ַ���{��id����1}
            String jsID = jsStr.getString("access_token");//��ȡid��ֵ
        } catch (Exception e) {  
            result = "Get:����������ʧ�ܣ�";  
            e.printStackTrace();  
        }  
        // ʹ��finally�����ر�������  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
//        return result;  
	}
	
	public static String main(String url, String param) {  
        String result = "";  
        BufferedReader in = null;  
        try {  
            String urlName = "";  
            urlName = "http://qixin.osnyun.com:9090/eim/api/get_token?agent_id=10030&agent_secret=2m12NmfeIQatc2QJjG7dqeL3FEKtieyV";
            //Log.i("ConnUtil", urlName);  
            System.out.println(urlName);  
            URL realUrl = new URL(urlName);  
            // �򿪺�URL֮�������  
            URLConnection conn = realUrl.openConnection();  
            // ����ʵ�ʵ�����  
            conn.connect();  
            // ��ȡ������Ӧͷ�ֶ�  
            Map< String,List< String>> map = conn.getHeaderFields();  
            // �������е���Ӧͷ�ֶ�  
            for (String key : map.keySet()) {  
                System.out.println(key + "--->" + map.get(key));  
            }  
            // ����BufferedReader����������ȡURL����Ӧ  
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += "\n" + line;  
            }  
        } catch (Exception e) {  
            result = "Get:����������ʧ�ܣ�";  
            e.printStackTrace();  
        }  
        // ʹ��finally�����ر�������  
        finally {  
            try {  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
	
	public static String postHttpReq(String url,String json) throws ClientProtocolException, IOException{
		String conResult = "";
		HttpClient httpclient = new DefaultHttpClient();  
		        HttpPost httppost = new HttpPost(url); 
		    httppost.setEntity(new StringEntity(json,"UTF-8"));
		    HttpResponse response = httpclient.execute(httppost);
		    if (response.getStatusLine().getStatusCode() == 200) {  
		        //����������  
		     conResult = EntityUtils.toString(response  
		                .getEntity());  
		    } else {  
		     throw new RuntimeException("http request error:" + response.getStatusLine().getStatusCode());
		    }  
		        return conResult;
		}
}
