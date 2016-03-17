package flume;

import org.apache.flume.node.Application;

/**
 * Created by guor on 2016/3/17.
 */
public class FlumeTest4Producer {
    public static void main(String[] args) {
        String agentName = "producer";
        PropertiesConfigurationProvider configurationProvider = new PropertiesConfigurationProvider(agentName, "flume.conf");
        Application application = new Application();
        application.handleConfigurationEvent(configurationProvider.getConfiguration());
        application.start();

        final Application appReference = application;
        Runtime.getRuntime().addShutdownHook(new Thread("agent-shutdown-hook") {
            @Override
            public void run() {
                appReference.stop();
            }
        });
    }
}
