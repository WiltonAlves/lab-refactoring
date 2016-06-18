package lab.refactoring.web.jersey;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import lab.refactoring.web.WebApplication;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
	  packages(WebApplication.class.getPackage().getName());
	}

}
