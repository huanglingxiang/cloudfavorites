package com.yc.web.listeners;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.yc.bean.Tag;
import com.yc.biz.FavoriteBiz;
import com.yc.biz.impl.FavoriteBizImpl;
  
/*
 * 当容器启动时就加载所有的标签... 使用临听器实现
 * 
 */
@WebListener
public class InitListener implements ServletContextListener {  //ServletContext -> application

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
	//创建FavoriteBizImpl对象，调用findAllTag()方法获取所有的List<Tag> 存到appliction中
	//TODO:以后再添加Tag,将要同步添加修改  application中的List<Tag>
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		FavoriteBiz tb=new FavoriteBizImpl();
		try {
			List<Tag> list=tb.findAllTag();
			arg0.getServletContext().setAttribute("alltags", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
