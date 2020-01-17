package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.wechat.service.impl.WechatServiceImpl;
 
public class HttpClientUtil {
  public static final String DEF_CHATSET = "UTF-8";
  public static String doGet(String httpurl) {
    HttpURLConnection connection = null;
    InputStream is = null;
    BufferedReader br = null;
    String result = null;// ���ؽ���ַ���
    try {
      // ����Զ��url���Ӷ���
      URL url = new URL(httpurl);
      // ͨ��Զ��url���Ӷ����һ�����ӣ�ǿת��httpURLConnection��
      connection = (HttpURLConnection) url.openConnection();
      // �������ӷ�ʽ��get
      connection.setRequestMethod("GET");
      // �������������������ĳ�ʱʱ�䣺15000����
      connection.setConnectTimeout(15000);
      // ���ö�ȡԶ�̷��ص�����ʱ�䣺60000����
      connection.setReadTimeout(60000);
      // ��������
      connection.connect();
      // ͨ��connection���ӣ���ȡ������
      if (connection.getResponseCode() == 200) {
        is = connection.getInputStream();
        // ��װ������is����ָ���ַ���
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        // �������
        StringBuffer sbf = new StringBuffer();
        String temp = null;
        while ((temp = br.readLine()) != null) {
          sbf.append(temp);
          sbf.append("\r\n");
        }
        result = sbf.toString();
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // �ر���Դ
      if (null != br) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
 
      if (null != is) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
 
      connection.disconnect();// �ر�Զ������
    }
 
    return result;
  }
 
  public static String doPost(String httpUrl, String param) {
 
    HttpURLConnection connection = null;
    InputStream is = null;
    OutputStream os = null;
    BufferedReader br = null;
    String result = null;
    try {
      URL url = new URL(httpUrl);
      // ͨ��Զ��url���Ӷ��������
      connection = (HttpURLConnection) url.openConnection();
      // ������������ʽ
      connection.setRequestMethod("POST");
      // ��������������������ʱʱ�䣺15000����
      connection.setConnectTimeout(15000);
      // ���ö�ȡ�����������������ݳ�ʱʱ�䣺60000����
      connection.setReadTimeout(60000);
 
      // Ĭ��ֵΪ��false������Զ�̷�������������/д����ʱ����Ҫ����Ϊtrue
      connection.setDoOutput(true);
      // Ĭ��ֵΪ��true����ǰ��Զ�̷����ȡ����ʱ������Ϊtrue���ò������п���
      connection.setDoInput(true);
      // ���ô�������ĸ�ʽ:�������Ӧ���� name1=value1&name2=value2 ����ʽ��
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      // ���ü�Ȩ��Ϣ��Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
      connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
      // ͨ�����Ӷ����ȡһ�������
      os = connection.getOutputStream();
      // ͨ����������󽫲���д��ȥ/�����ȥ,����ͨ���ֽ�����д����
      os.write(param.getBytes(DEF_CHATSET));//����Ĭ��ΪUTF-8
      // ͨ�����Ӷ����ȡһ������������Զ�̶�ȡ
      if (connection.getResponseCode() == 200) {
 
        is = connection.getInputStream();
        // ��������������а�װ:charset���ݹ�����Ŀ���Ҫ��������
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
 
        StringBuffer sbf = new StringBuffer();
        String temp = null;
        // ѭ������һ��һ�ж�ȡ����
        while ((temp = br.readLine()) != null) {
          sbf.append(temp);
          sbf.append("\r\n");
        }
        result = sbf.toString();
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      // �ر���Դ
      if (null != br) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (null != os) {
        try {
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (null != is) {
        try {
          is.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      // �Ͽ���Զ�̵�ַurl������
      connection.disconnect();
    }
    return result;
  }
  /**
 * @Description: �ϴ�΢����ʱ�ز�
 * @Author ��ƽ
 * @Date 2019��12��25�� ����8:41:38
 * @param path
 * @param type
 * @return
 */
public static String uplodeWechatTempMaterial(String path, String type) {
	File file = new File(path);
	// ��ַ
	String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	url = url.replace("ACCESS_TOKEN", WechatServiceImpl.getAccessToken()).replace("TYPE", type);
	try {
		URL urlObject  = new URL(url);
		//ǿתΪhttps����
		HttpsURLConnection connection = (HttpsURLConnection)urlObject.openConnection();
		//����������Ϣ
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false); 
		//��������ͷ��Ϣ
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("Charset", "utf8");
		//���ݵı߽�
		String boundary = "-----"+System.currentTimeMillis();
		connection.setRequestProperty("Content-type", "multipart/form-data;boundary="+boundary);
		//��ȡ�����
		OutputStream out = connection.getOutputStream();
		//�����ļ�������
		InputStream is = new FileInputStream(file);//FileInputStream�̳�InputStream
		//��һ���֣�ͷ����Ϣ
		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(boundary);
		sb.append("\r\n");
		sb.append("Content-Disposition:form-data;name=\"media\";filename=\""+file.getName()+"\"");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		out.write(sb.toString().getBytes());
		//�ڶ����֣��ļ�����
		byte[] b = new byte[1024];
		int len;
		while ((len=is.read(b))!=-1) {
			out.write(b, 0, len);
		}
		is.close();
		//�������֣�β����Ϣ
		String foot="\r\n--"+boundary+"--\r\n";
		out.write(foot.getBytes());
		out.flush();
		out.close();
		//��ȡ����
		InputStream inputStream = connection.getInputStream();
		StringBuilder stringBuilder = new StringBuilder();
		while ((len=inputStream.read(b))!=-1) {
			stringBuilder.append(new String(b,0,len));
		}
		return stringBuilder.toString()   ;
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
  } 
}