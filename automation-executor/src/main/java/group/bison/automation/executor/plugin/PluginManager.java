package group.bison.automation.executor.plugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by BSONG on 2018/6/4.
 */
public class PluginManager {
    private static final Logger LOG = LoggerFactory.getLogger(PluginManager.class);

    private static Map<String, PluginClassLoader> pluginMap = new HashMap<>();
    private static String pluginDir = null;

    public static PluginClassLoader load(String plugin) {
        if (pluginMap.containsKey(plugin)) {
            return pluginMap.get(plugin);
        }

        try {
            PluginClassLoader pluginClassLoader = new PluginClassLoader();
            pluginClassLoader.addURLFile(new URL(String.join("", "jar:file://", pluginDir, "/", plugin, ".jar!/")));
            pluginMap.put(plugin, pluginClassLoader);
            return pluginClassLoader;
        } catch (Exception e) {
            LOG.info("Failed to load plugin:{}", plugin);
            return null;
        }
    }

    public static boolean unload(String plugin) {
        if (!pluginMap.containsKey(plugin)) {
            return false;
        }

        boolean success = true;
        try {
            PluginClassLoader pluginClassLoader = pluginMap.remove(plugin);
            pluginClassLoader.unloadJarFiles();
        } catch (Exception e) {
            LOG.info("Failed to unload plugin:{}", plugin);
            success = false;
        }
        return success;
    }


    public static class PluginClassLoader extends URLClassLoader {

        private List<JarURLConnection> cachedJarFiles = new LinkedList<>();

        PluginClassLoader() {
            super(new URL[]{}, findParentClassLoader());
        }

        void addURLFile(URL file) {
            try {
                // 打开并缓存文件url连接
                URLConnection uc = file.openConnection();
                if (uc instanceof JarURLConnection) {
                    uc.setUseCaches(true);
                    ((JarURLConnection) uc).getManifest();
                    cachedJarFiles.add((JarURLConnection) uc);
                }
            } catch (Exception e) {
            }
            addURL(file);
        }

        /**
         * 卸载jar包
         */
        void unloadJarFiles() {
            for (JarURLConnection url : cachedJarFiles) {
                try {
                    LOG.info("Unloading plugin JAR file {}", url.getJarFile().getName());
                    url.getJarFile().close();
                    url = null;
                } catch (Exception e) {
                    LOG.error("Failed to unload JAR file", e);
                }
            }
        }

        private static ClassLoader findParentClassLoader() {
            ClassLoader parent = PluginManager.class.getClassLoader();
            if (parent == null) {
                parent = PluginClassLoader.class.getClassLoader();
            }
            if (parent == null) {
                parent = ClassLoader.getSystemClassLoader();
            }
            return parent;
        }
    }
}
