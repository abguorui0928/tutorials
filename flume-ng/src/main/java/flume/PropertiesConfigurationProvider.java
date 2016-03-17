package flume;

import org.apache.flume.conf.FlumeConfiguration;
import org.apache.flume.node.AbstractConfigurationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * properties from local resource
 * Created by guor on 2016/3/17.
 */
public class PropertiesConfigurationProvider extends AbstractConfigurationProvider {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(PropertiesConfigurationProvider.class);

    private String fileName;

    public PropertiesConfigurationProvider(String agentName, String fileName) {
        super(agentName);
        this.fileName = fileName;
    }

    @Override
    protected FlumeConfiguration getFlumeConfiguration() {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(this.fileName);
        try {
            Properties properties = new Properties();
            properties.load(in);
            return new FlumeConfiguration(toMap(properties));
        } catch (IOException ex) {
            LOGGER.error("Unable to load file:" + fileName + " (I/O failure) - Exception follows.", ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    LOGGER.warn("Unable to close file reader for file: " + fileName, ex);
                }
            }
        }
        return new FlumeConfiguration(new HashMap<String, String>());
    }
}
