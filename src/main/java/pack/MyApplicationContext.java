package pack;

import java.util.Properties;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

public class MyApplicationContext extends AnnotationConfigApplicationContext {
	
	public MyApplicationContext(Class<?>... annotatedClasses) {
		super(annotatedClasses);
	}
	
	@Override
	protected void initPropertySources() {
		super.initPropertySources();
		ConfigurableEnvironment env = getEnvironment();
		Properties propertiesFromDb = new Properties();
		propertiesFromDb.put("value", "this value is coming from db");
		PropertySource<?> propertySource = new PropertiesPropertySource("properties from db", propertiesFromDb);
		env.getPropertySources().addFirst(propertySource);
	}

}
