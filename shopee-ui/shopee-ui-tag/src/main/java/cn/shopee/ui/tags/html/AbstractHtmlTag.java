package cn.shopee.ui.tags.html;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import cn.shopee.beetl.tags.TagSupport;
import cn.shopee.beetl.tags.exception.BeetlTagException;
import cn.shopee.ui.tags.form.support.FreemarkerFormTagHelper;
import cn.shopee.ui.tags.html.manager.HtmlComponentManager;
import cn.shopee.common.utils.SpringContextHolder;
import cn.shopee.common.utils.StringUtils;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * All rights Reserved, Designed By www.shopee.cn
 * 
 * @title: ComponentTag.java
 * @package cn.shopee.tags.html
 * @description: 组建获取标签
 * @author: HuLiang
 * @date: 2017年5月13日 上午9:05:06
 * @version V1.0
 * @copyright: 2017 www.shopee.cn Inc. All rights reserved.
 *
 */
public abstract class AbstractHtmlTag extends TagSupport {

	private static final String[] SUPPORT_TYPES = { "CSS", "JS" };
	private String name = ""; // 组件名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * 获取支持的html
	 */
	public String[] getHtmlComponents() {
		return null;
	}

	/**
	 * 
	 * @title: getSupportTypes
	 * @description: 获取支持的类型
	 * @return
	 * @return: String[]
	 */
	public abstract String[] getSupportTypes();

	@Override
	public int doStartTag() throws BeetlTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws BeetlTagException {
		try {
			// 初始化数据
			String content = "";
			if (StringUtils.isEmpty(name)){
				return EVAL_PAGE;
			}
			String[] components = name.split(",");
			for (String component : components) {
				if (isComponent(component)) {
					String[] types = getSupportTypes();
					if (types == null) {
						types = SUPPORT_TYPES;
					}
					for (String type : types) {
						String componentContent = getComponentHtml(component.toLowerCase(), type);
						if (!StringUtils.isEmpty(componentContent)) {
							content += componentContent + "\r\n";
						}
					}
				}
			}
			content = parseContent(content);
			this.ctx.byteWriter.writeString(content);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getComponentHtml(String component, String type) {
		HtmlComponentManager htmlComponentManager = SpringContextHolder.getApplicationContext()
				.getBean(HtmlComponentManager.class);
		// String ftl = "/tags/html/" + type.toLowerCase() + "/" +
		// component.toLowerCase() + ".flt";
		try {
			// String content =
			// FreeMarkerUtils.initByDefaultTemplate().processToString(ftl,
			// rootMap);
			String content = "";
			if (type.equals("CSS")) {
				content = htmlComponentManager.getCssComponent(component);
			} else if (type.equals("JS")) {
				content = htmlComponentManager.getJsComponent(component);
			} else if (type.equals("FRAGMENT")) {
				content = htmlComponentManager.getFragmentComponent(component);
			}
			return content;
		} catch (Exception e) {
			return null;
		}
	}

	private String parseContent(String content) throws TemplateException, IOException {
		Map<String, Object> rootMap = FreemarkerFormTagHelper.getTagStatic(this, this.ctx);
		//文件上传
		// PropertiesUtil propertiesUtil = new PropertiesUtil("upload.properties");
		// String ueditorUploadUrl= propertiesUtil.getString("upload.ueditor.url");
		// rootMap.put("ueditorUploadUrl", ueditorUploadUrl);
		String tempname = StringUtils.hashKeyForDisk(content);
		Configuration configuration = new Configuration();
		configuration.setNumberFormat("#");
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate(tempname, content);
		@SuppressWarnings("deprecation")
		Template template = new Template(tempname, new StringReader(content));
		StringWriter stringWriter = new StringWriter();
		template.process(rootMap, stringWriter);
		configuration.setTemplateLoader(stringLoader);
		content = stringWriter.toString();
		return content;
	}

	/**
	 * 
	 * @title: isComponent
	 * @description:是否为组件
	 * @param name
	 * @return
	 * @return: boolean
	 */
	private boolean isComponent(String name) {
		/*
		 * if (StringUtils.isEmpty(name)) { return false; } for (String
		 * component : HTML_COMPONENTS) { if (component.equals(name.trim())) {
		 * return true; } } // 扩展中的 String[] htmlComponents =
		 * getHtmlComponents(); if (htmlComponents != null) { for (String
		 * component : HTML_COMPONENTS) { if (component.equals(name.trim())) {
		 * return true; } } }
		 */

		return true;
	}

}