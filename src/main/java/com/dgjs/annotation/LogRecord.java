package com.dgjs.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import com.dgjs.model.enums.OperateEnum;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecord {

	/*
	 * 描述
	 */
	String remark() default "";
	
	/*
	 * 事件
	 */
	int event() default -1;

	/*
	 * 操作类型
	 */
	OperateEnum operate();
	
}
