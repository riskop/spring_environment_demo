package pack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class Start {

	public static void main(String[] args) {
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Conf.class);
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.getEnvironment().getPropertySources().addFirst(getDbBasedPropertySource());
		ctx.scan("pack");
		//ctx.register(Conf.class);
		ctx.refresh();
//		AnnotationConfigApplicationContext ctx = new MyApplicationContext(Conf.class);
		
		ConfigurableEnvironment env = ctx.getEnvironment();
		System.out.println("environment profiles: " + Arrays.asList(env.getActiveProfiles()));
		
		Iterator<PropertySource<?>> i = env.getPropertySources().iterator();
		while(i.hasNext()) {
			PropertySource<?> propertySource = i.next();
			System.out.println("propertySource: " + propertySource);
			System.out.println("underlying source: " + propertySource.getSource());
		}
		
		App app = ctx.getBean(App.class);
		app.doSomething();
		ctx.close();
	}
	
	private static PropertySource<?> getDbBasedPropertySource() {
		Properties propertiesFromDb = new Properties();
		propertiesFromDb.put("value", "this value is coming from db");
		PropertySource<?> propertySource = new PropertiesPropertySource("properties from db", propertiesFromDb);
		return propertySource;
	}
	
}
