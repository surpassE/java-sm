package com.sirding.core.utils;

import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
 
/**
 * @Described	: 获得ApplicationContext和Bean的工具类
 * @project		: com.sirding.core.utils.ACUtils
 * @author 		: zc.ding
 * @date 		: 2016年11月29日
 */
public class ACUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
 
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }
 
    /**
     * @Described	: 获取applicationContext对象
     * @author		: zc.ding
     * @date 		: 2016年11月29日
     * @return		: ApplicationContext
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
     
    /**
     * @Described	: 根据bean的id来查找对象
     * @author		: zc.ding
     * @date 		: 2016年11月29日
     * @return		: Object
     * @param id	: 
     */
    public static Object getBean(String id){
        return applicationContext.getBean(id);
    }
     
    /**
     * @Described	: 根据bean的class来查找对象
     * @author		: zc.ding
     * @date 		: 2016年11月29日
     * @return		: Object
     * @param c
     * @return
     */
    public static <T> T getBean(Class<T> c){
        return applicationContext.getBean(c);
    }
    
    /**
     * @Described			: 从上下文中通过name获得指定的实例对象
     * @author				: zc.ding
     * @date 				: 2016年11月29日
     * @return				: T
     * @param name			:
     * @param clazz			:
     */
    public static <T> T getBean(String name, Class<T> clazz){
    	return applicationContext.getBean(name, clazz);
    }

    /**
     * @Described	: 根据bean的class来查找所有的对象(包括子类)
     * @author		: zc.ding
     * @date 		: 2016年11月29日
     * @return		: Map<String,?>
     * @param clazz
     * @return
     */
    public static Map<String, ?> getBeansByClass(Class<?> clazz){
        return applicationContext.getBeansOfType(clazz);
    }
}
