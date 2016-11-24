package com.sirding.core.utils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Described	: 反射工具类
 * @project		: com.sirding.core.utils.ReflectUtil
 * @author 		: zc.ding
 * @date 		: 2016年11月24日
 */
public class ReflectUtil<E> {
	
	/**
	 * @Described	: 调用obj的指定接口
	 * @author		: zc.ding
	 * @date 		: 2016年11月24日
	 * @param 		：obj			还有被调用的接口的实现类
	 * @param 		：methodName		被调用的方式
	 * @param 		：args			接口需要的参数
	 */
	public static void call(Object obj, String methodName, Object...args){
		Method method = getMethod(obj, methodName, args);
		if(method != null){
			try {
				method.invoke(obj, args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @Described	: 调用obj的指定接口，返回<E>对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月24日
	 * @param 		：obj			还有被调用的接口的实现类
	 * @param 		：methodName		被调用的方式
	 * @param 		：args			接口需要的参数
	 * @return		: E
	 */
	@SuppressWarnings({"unchecked" })
	public static <E> E callForEntity(Object obj, String methodName, Object...args){
		Method method = getMethod(obj, methodName, args);
		if(method != null){
			try {
				return (E)method.invoke(obj, args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * @Described	: 调用指定的接口返回List<E>类型的集合
	 * @author		: zc.ding
	 * @date 		: 2016年11月24日
	 * @return		: E
	 * @param 		：obj			还有被调用的接口的实现类
	 * @param 		：methodName		被调用的方式
	 * @param 		：args			接口需要的参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> List<E> callForList(Object obj, String methodName, Object...args){
		Method method = getMethod(obj, methodName, args);
		if(method != null){
			try {
				return (List<E>)method.invoke(obj, args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * @Described	: 获得要调用的Method对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月24日
	 * @param 		：obj			还有被调用的接口的实现类
	 * @param 		：methodName		被调用的方式
	 * @param 		：args			接口需要的参数
	 * @return		: List<E>
	 */
	private static Method getMethod(Object obj, String methodName, Object...args){
		Class<?> clazz = obj.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		
		if(methods != null && methods.length > 0){
			loop:
			for(Method method : methods){
				if(method.getName().equals(methodName)){	//找到要调用的方法名称
					if(args != null){
						Class<?>[] parameters = method.getParameterTypes();
						//判断参数个数是否相等
						if(args.length != parameters.length){
							continue;
						}
						for(int i = 0; i < args.length; i++){
							Object object = args[i];
							//判断参数类型是否相等
							if(!object.getClass().toString().equals(parameters[i].toString())){
								continue loop;
							}
						}
						return method;
					}
				}
			}
		}
		return null;
	}
	
}
