package com.wechat.model.menu;

/**
 * @Description: 视图菜单
 * @Author 熊平
 * @Date 2019年12月24日
 */
public class ViewButton extends BaseButton{
		private String type = "view";//网页菜单的类型
		private String url ;//网页菜单的url
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
