import java.util.HashMap;
import java.util.Iterator;















import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import com.util.HttpClientUtil;
import com.wechat.service.impl.WechatServiceImpl;


public class testAI {

	/*@Test
	public void test() {*/
		@Test
		public void sample() {
			AipOcr client = new AipOcr("18101156", "NYKGj2DYLWfGtR0XxP00tuEW", "cg98wYUc4tvDxGrZCfnUPMn8Td1qLAsQ");
		    // �����ѡ�������ýӿ�
		    HashMap<String, String> options = new HashMap<String, String>();
		    options.put("language_type", "CHN_ENG");
		    options.put("detect_direction", "true");
		    options.put("detect_language", "true");
		    options.put("probability", "true");
		    
		    
		    // ����Ϊ����ͼƬ·��
		    String image = "E:/PS�ز�/testAI.png";
		    org.json.JSONObject  res = client.basicGeneral(image, options);
		    JSONObject json = JSONObject.parseObject(res.toString());
		    JSONArray jsonArray = json.getJSONArray("words_result");
		    StringBuilder sbBuilder = new StringBuilder();
		    for (int i = 0; i < jsonArray.size(); i++) {
		    	JSONObject jsonObject = jsonArray.getJSONObject(i);
		    	sbBuilder.append(jsonObject.getString("words"));
			}	
		    System.out.println(sbBuilder.toString());

		  /*  // ����Ϊ����ͼƬ����������
		    byte[] file = readImageFile(image);
		    res = client.basicGeneral(file, options);
		    System.out.println(res.toString(2));*/

		    
		   /* // ͨ������ʶ��, ͼƬ����ΪԶ��urlͼƬ
		    JSONObject res = client.basicGeneralUrl(url, options);
		    System.out.println(res.toString(2));*/

		}
	/*}*/
		/**
		 * @Description: ΢���ϴ���ʱͼƬ
		 * @Author ��ƽ
		 * @Date 2019��12��25�� ����9:16:41
		 */
		@Test
	public void	testUplode(){
			String file = "E:/PS�ز�/1.jpg";
			String uplodeWechatTempMaterial = HttpClientUtil.uplodeWechatTempMaterial(file, "image");
			System.out.println(uplodeWechatTempMaterial);
		
	}	
		@Test
	public void	testDownlode(){
		String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		url=url.replace("ACCESS_TOKEN", "28_yNZkEjF5BWJDVre9I1-uviV1w2OHHEKALV6MstabXzecwiTpPvTY5ic5Xuz5pbK3esHHoHI2aS_FIo8ixrwcNgbqTHZgNL9Z_onSCIZDNysxEFejoX-Daf53tNz1d5w5-z3EbdkSPDSA1wuEJWAgAAABOS").replace("MEDIA_ID", "FASDyKjh-nWzCP5_5AvAfQ3LhcqIliNnp4TWIkWo2jqktfzRblNUVFjb4GWN9Xqn");
		String doGet = HttpClientUtil.doGet(url);
		System.out.println(doGet);
		}
		@Test
		public void getToken(){
			System.out.println(WechatServiceImpl.getAccessToken());
		}
		@Test
	public void testTicket(){
		WechatServiceImpl wechatServiceImpl = new WechatServiceImpl();
		String qrCodeTicket = wechatServiceImpl.getQrCodeTicket();
		System.out.println(qrCodeTicket);
	}
		@Test
		public void testgetUserInfo(){
			WechatServiceImpl wechatServiceImpl = new WechatServiceImpl();
			String openid="oIy6zwV8-MMAIQyvn6tCNT1kOc7c";
			String qrCodeTicket = wechatServiceImpl.getWechatUserInfo(openid);
			System.out.println(qrCodeTicket);
		}
}
