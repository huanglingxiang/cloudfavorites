package com.yc.web.servlets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



public class  RequestUtil<T> {
	public static <T> T getParameter(Map<String,Object> request,Class<T> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//根据c创建一个对象，这个对象用于request所有的值
		T obj=c.newInstance(); //实例化
		//取出c中所有的set方法
		List<Method> setMethods=getAllSetMethods(c);
		//将setMethods中的每个方法的set去掉，并将首字母转小写，存到一个集合中 setUname()->uname  
		List<String> params=getAllParameter(setMethods);
			for(String p:params){
				if(request.get(p)==null){
					continue;
				}
				String value=request.get(p).toString();
				if(value!=null&&!"".equals(value)){
					for(Method m: setMethods){
						//判断是哪一个set方法要运行起来
						if(m.getName().equalsIgnoreCase("set"+p)){
							//判断参数的类型
							Class typeClass=m.getParameterTypes()[0];//因为set方法是标准的javabean方法，它的参数有且只有一个
							String typeClassName=typeClass.getName();
							if("int".equals(typeClassName) || "java.lang.Integer".equals(typeClassName)){
								int v=Integer.parseInt(value);
								m.invoke(obj, v);
							}else if("float".equals(typeClassName) || "java.lang.Float".equals(typeClassName)){
								float v=Float.parseFloat(value);
								m.invoke(obj, v);
							}else if("double".equals(typeClassName) || "java.lang.Double".equals(typeClassName)){
								double v=Double.parseDouble(value);
								m.invoke(obj, v);
							}else{
								m.invoke(obj, value);//u.setUname(xxx);
							}
						}
					}
				}
			}
			return obj;
	}
	
	/**
	 * 从request中取出所有的参数，将参数存到一个Object对象
	 * @param request
	 * @param c
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static <T>T getParemeter(HttpServletRequest request,Class<T> c) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		//根据c创建一个对象，这个对象用于request所有的值
		T obj=c.newInstance();
		//取出c中所有的set方法
		List<Method> setMethods=getAllSetMethods(c);
		//将setMethods中的每个方法的set去掉，并将首字母转小写，存到一个集合中 setUname()->uname  
		List<String> params=getAllParameter(setMethods);
		//从request中取出所有的参数
		//request.getParameterMap();
		for(String p: params){
			String value=request.getParameter(p);
			if(value!=null&&!"".equals(value)){
				for(Method m: setMethods){
					//判断是哪一个set方法要运行起来
					if(m.getName().equalsIgnoreCase("set"+p)){
						//判断参数的类型
						Class typeClass=m.getParameterTypes()[0];//因为set方法是标准的javabean方法，它的参数有且只有一个
						String typeClassName=typeClass.getName();
						if("int".equals(typeClassName) || "java.lang.Integer".equals(typeClassName)){
							int v=Integer.parseInt(value);
							m.invoke(obj, v);
						}else if("float".equals(typeClassName) || "java.lang.Float".equals(typeClassName)){
							float v=Float.parseFloat(value);
							m.invoke(obj, v);
						}else if("double".equals(typeClassName) || "java.lang.Double".equals(typeClassName)){
							double v=Double.parseDouble(value);
							m.invoke(obj, v);
						}else{
							m.invoke(obj, value);//u.setUname(xxx);
						}
					}
				}
			}
		}
		return obj;
	}
	
	
	/**
	 * 将setMethods中的每个方法的set去掉，并将首字母转小写，存到一个集合中 setUname()->uname
	 * @param setMethods
	 * @return
	 */
	public static List<String> getAllParameter(List<Method> setMethods){
		List<String> list=new ArrayList<String>();
		for(Method m:setMethods){
			String pname=m.getName().substring(3, m.getName().length());
			pname=pname.substring(0, 1).toLowerCase()+pname.substring(1);
			list.add(pname);
		}
		return list;
	}
	
	
	/**
	 * 取出一个类中的所有set方法
	 * @param c
	 * @return
	 */
	public static <T>List<Method> getAllSetMethods(Class<T> c){
		List<Method> setMethods=new ArrayList<Method>();
		if(c!=null){
			Method[] ms=c.getMethods();//取出User类中的所有方法，但只要set
			for(Method m:ms){
				if(m.getName().startsWith("set")){//如果方法名以set开头，则保存到setMethodS
					setMethods.add(m);
				}
			}
		}
		return setMethods;
	}
	
	
	/**
	 * 取出一个类中的所有get方法
	 * @param c
	 * @return
	 */
	public static <T> List<Method> getAllGetMethods(Class<T> c){
		List<Method> getMethods=new ArrayList<Method>();
		if(c!=null){
			Method[] ms=c.getMethods();//取出User类中的所有方法，但只要get
			for(Method m:ms){
				if(m.getName().startsWith("get")){//如果方法名以set开头，则保存到setMethodS
					getMethods.add(m);
				}
			}
		}
		return getMethods;
	}
	
}
