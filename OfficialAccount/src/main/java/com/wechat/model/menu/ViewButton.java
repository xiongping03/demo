package com.wechat.model.menu;

/**
 * @Description: ��ͼ�˵�
 * @Author ��ƽ
 * @Date 2019��12��24��
 */
public class ViewButton extends BaseButton{
		private String type = "view";//��ҳ�˵�������
		private String url ;//��ҳ�˵���url
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public ViewButton(String name,String url) {
			super(name);
			// TODO Auto-generated constructor stub
			this.url =url;
		}
		
}
