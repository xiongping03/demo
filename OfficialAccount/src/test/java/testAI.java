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
		    // 传入可选参数调用接口
		    HashMap<String, String> options = new HashMap<String, String>();
		    options.put("language_type", "CHN_ENG");
		    options.put("detect_direction", "true");
		    options.put("detect_language", "true");
		    options.put("probability", "true");
		    
		    
		    // 参数为本地图片路径
		    String image = "E:/PS素材/testAI.png";
		    org.json.JSONObject  res = client.basicGeneral(image, options);
		    JSONObject json = JSONObject.parseObject(res.toString());
		    JSONArray jsonArray = json.getJSONArray("words_result");
		    StringBuilder sbBuilder = new StringBuilder();
		    for (int i = 0; i < jsonArray.size(); i++) {
		    	JSONObject jsonObject = jsonArray.getJSONObject(i);
		    	sbBuilder.append(jsonObject.getString("words"));
			}	
		    System.out.println(sbBuilder.toString());

		  /*  // 参数为本地图片二进制数组
		    byte[] file = readImageFile(image);
		    res = client.basicGeneral(file, options);
		    System.out.println(res.toString(2));*/

		    
		   /* // 通用文字识别, 图片参数为远程url图片
		    JSONObject res = client.basicGeneralUrl(url, options);
		    System.out.println(res.toString(2));*/

		}
	/*}*/
		/**
		 * @Description: 微信上传临时图片
		 * @Author 熊平
		 * @Date 2019年12月25日 下午9:16:41
		 */
		@Test
	public void	testUplode(){
			String file = "E:/PS素材/1.jpg";
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
