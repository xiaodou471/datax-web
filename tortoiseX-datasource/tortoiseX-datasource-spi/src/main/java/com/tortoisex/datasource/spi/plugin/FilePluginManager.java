package com.tortoisex.datasource.spi.plugin;

import com.tortoisex.datasource.spi.Constants;
import com.tortoisex.datasource.spi.TortoiseXDatasourcePlugin;
import com.tortoisex.datasource.spi.api.DatasourcePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FilePluginManager
 */
public class FilePluginManager implements PluginManager {

    private static final Logger logger = LoggerFactory.getLogger(FilePluginManager.class);

    private Map<String, DatasourcePlugin> pluginMap = new ConcurrentHashMap<>();

    private Map<String, ServiceLoader<TortoiseXDatasourcePlugin>> pluginLoaderMap = new ConcurrentHashMap<>();

    private Map<String, PluginClassLoader> classLoaderMap = new ConcurrentHashMap<>();

    private String[] whitePrefixes;

    private String[] excludePrefixes;

    public FilePluginManager(String dirPath, String[] whitePrefixes, String[] excludePrefixes) {
        this.whitePrefixes = whitePrefixes;
        this.excludePrefixes = excludePrefixes;
        try {
            load(dirPath);
        } catch (MalformedURLException e) {
            logger.error("load plugins failed.", e);
        }
    }

    private void load(String dirPath) throws MalformedURLException {
        logger.info("start to load jar files in {}", dirPath);
        if (dirPath == null) {
            logger.error("not a valid path - {}", dirPath);
            return;
        }
        File[] files = new File(dirPath).listFiles();
        if (files == null) {
            logger.error("not a valid path - {}", dirPath);
            return;
        }
        for (File file : files) {
            if (file.isDirectory() && !file.getPath().endsWith(Constants.PLUGIN_JAR_SUFFIX)) {
                continue;
            }
            String pluginName = file.getName()
                    .substring(0, file.getName().length() - Constants.PLUGIN_JAR_SUFFIX.length());
            URL[] urls = new URL[]{ file.toURI().toURL() };
            PluginClassLoader classLoader =
                    new PluginClassLoader(urls, Thread.currentThread().getContextClassLoader(), whitePrefixes, excludePrefixes);
            classLoaderMap.put(pluginName, classLoader);

            ServiceLoader<TortoiseXDatasourcePlugin> loader = ServiceLoader.load(TortoiseXDatasourcePlugin.class, classLoader);
            pluginLoaderMap.put(pluginName, loader);

            loader.forEach(provider -> {
                DatasourcePlugin plugin = provider.createPlugin();
                pluginMap.put(plugin.getId(), plugin);
                logger.info("loaded plugin - {}", plugin.getId());
            });
        }
    }

    @Override
    public DatasourcePlugin findOne(String pluginId) {
        return pluginMap.get(pluginId);
    }

    @Override
    public Map<String, DatasourcePlugin> findAll() {
        return pluginMap;
    }

    @Override
    public void addPlugin(DatasourcePlugin plugin) {
        pluginMap.put(plugin.getId(), plugin);
    }

}
