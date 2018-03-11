package REST;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Swagam
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(REST.Game.class);
        resources.add(REST.GenericResource.class);
        resources.add(REST.Login.class);
    }
    
}
