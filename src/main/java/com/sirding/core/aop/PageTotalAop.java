package com.sirding.core.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.sirding.domain.PageAdapter;

@Aspect
public class PageTotalAop {

	private final Logger logger = Logger.getLogger(getClass());
	
//	@Pointcut("@annotation(com.sirding.aop.PageTotal)")
	@Pointcut("@annotation(org.springframework.cache.annotation.Cacheable)")
	public void pointCut(){}


	@Before("pointCut()")
	public void before(JoinPoint point){
		logger.debug("执行Before===" + point.getTarget() + "==" + point.getSignature().getName());
		if("preHandle".equals(point.getSignature().getName())){
		}
	}

	@AfterReturning("pointCut()")
	//	@AfterReturning("(execution(* com.surpass.business.usermanager.service.impl.UserServiceImpl.*(..)))")
	public void afterReturning(JoinPoint joinPoint){
		Object[] objects = joinPoint.getArgs();
		if(objects != null){
			for(Object obj : objects){
				if(obj instanceof PageAdapter){
					//结果的总条数放到缓存中
					
				}
			}
		}
	}

//	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint){
		Object object = null;
		try {
			object = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		return object;
	}
}
