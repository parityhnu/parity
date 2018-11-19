package com.binqing.utilproject.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
	String name() default "";
	int length() default 100;
	//构造参数中的参数顺序，须与表中的order从小到大的顺序一致
	int order();
}
