package cn.mob.gamerec.queue.server;

import cn.mob.gamerec.queue.server.anno.ACTION;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ActionRegistry {
    private static final Logger LOGGER = Logger.getLogger(ActionRegistry.class);
    private final Map<String, Action> mapping = new HashMap<String, Action>();

    public void mapping(String uri, Action action) {
        mapping.put(uri, action);
    }

    public Action lookup(String uri) {
        return mapping.get(uri);
    }

    public void mappingPackage(String packageName) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Set<Class<?>> set = ClassLoaderUtils.getClasses(packageName);
        for (Class<?> clzz : set) {
            if (!Action.class.isAssignableFrom(clzz)) {
                continue;
            }

            ACTION action = clzz.getAnnotation(ACTION.class);
            if (action == null) {
                LOGGER.warn("[" + clzz.getName() + "] is assignable from Action,but not found 'ACTOIN' annotation.");
                continue;
            }

            String uri = action.path();
            Object obj = clzz.newInstance();
            LOGGER.debug("mapping url : " + uri);
            mapping(uri, (Action) obj);

        }
    }
}
