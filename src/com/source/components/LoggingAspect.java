package com.source.components;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.source.dao.LogMapper;
import com.source.model.Log;
import com.source.model.User;


@Aspect
@Component
public class LoggingAspect {
	
	@Resource
	private LogMapper logMapper;
	
	@Pointcut("execution(* com.source.interfaces.IService.*(..))")
	public void declareJointPointExpression(){}
	
	
	/**
	 * 在 com.service.IService 接口的每一个实现类的每一个方法执行后执行以下一段代码. 无论该方法是否出现异常
	 */
	@After("declareJointPointExpression()")
	public void afterMethod(JoinPoint joinPoint){
		String methodName = joinPoint.getSignature().getName();
		Object [] args = joinPoint.getArgs();
		//System.out.println("The method " + methodName + " ends"+Arrays.asList(args).toString());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operateTime = df.format(new Date());
		Log log = new Log();
		
		//log.setUserName("1");
		log.setUser_account(SessionUser.getUser().getAccount());
		log.setOperate_time(operateTime);
		String content="方法名称【"+methodName+"】操作数据："+Arrays.asList(args).toString();
		log.setContent(content);
		try {
			logMapper.addLog(log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 在目标方法出现异常时会执行的代码.
	 * 可以访问到异常对象; 且可以指定在出现特定异常时在执行通知代码
	*/ 
	@AfterThrowing(value="declareJointPointExpression()",throwing="e")
	public void afterThrowing(JoinPoint joinPoint, Exception e){
		String methodName = joinPoint.getSignature().getName();
		//System.out.println("The method " + methodName + " occurs excetion:" + exception);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String operateTime = df.format(new Date());
		Log log = new Log();
		User user = SessionUser.getUser();
		log.setUser_account(user.getAccount());
	//	log.setUser_account("1");
		log.setOperate_time(operateTime);
		String content="方法名称【"+methodName+"】异常信息："+e;
		log.setContent(content);
		try {
			logMapper.addLog(log);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
	}
	
	
}
