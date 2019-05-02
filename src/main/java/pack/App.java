package pack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class App {

	@Value("${java.runtime.name}")
	private String javaRuntimeName;
	
	@Value("${value}")
	private String value;

	public void doSomething() {
		System.out.println("app is doing something");
		System.out.println("javaRuntimeName: " + javaRuntimeName);
		System.out.println("value: " + value);
	}
	
}
