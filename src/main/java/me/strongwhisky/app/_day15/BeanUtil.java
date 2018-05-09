package me.strongwhisky.app._day15;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project hibernate
 * @since 2018-05-09
 */
@Service
public class BeanUtil implements ApplicationContextAware {
	private static ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}
}