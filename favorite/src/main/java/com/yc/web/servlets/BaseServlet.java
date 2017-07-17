package com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * 适配器
 * @author 阿达
 *
 */
public class BaseServlet extends HttpServlet {
	protected String charset="utf-8";
	protected String op;
	
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		op=req.getParameter("op");
		super.service(req, resp);
	}

	@Override //doGET方法处理get 请求方式
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	/**
	 * 只能用于 gson解析非泛型数据
	 * @param obj
	 * @param resp
	 * @throws IOException
	 */
	//这段代码的详细作用:利用gson中的toJson方法将对象转换为json字符串，利用io流提供的写入方法将json字符串写入resp对客户端的回复之中
	protected void outJson(Object obj,HttpServletResponse resp) throws IOException{
		Gson gson=new Gson();
		String jsonStr=gson.toJson(obj); //gson读取不出泛型数据
		outJsonStr(jsonStr,resp);
	}

	public void outJsonStr(String jsonStr, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out=resp.getWriter();
		out.println(jsonStr);
		out.flush();
		out.close();
	}
	
}
