
import controller.HomePageController;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


public class MyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        // add classes here
        // h.add(HomePageController.class);
        return h;
    }
}
