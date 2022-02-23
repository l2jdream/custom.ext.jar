package la2;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import l2.commons.configuration.ExProperties;

public class La2Config {

    private static final Logger LOG = LoggerFactory.getLogger(La2Config.class);
    public static boolean ACTIVE_HELP_SYSTEM;

    public static void load() {
        // load configuration file
        ExProperties config = initProperties("../Game/config/custom/La2config.properties");

        // set enable help
        ACTIVE_HELP_SYSTEM = config.getProperty("ActiveHelpSystem", false);
    }

    /**
     * try to load la2config.properties
     * 
     * @param filename
     * @return
     */
    private static final ExProperties initProperties(String filename) {
        ExProperties result = new ExProperties();

        try {
            result.load(new File(filename));
        } catch (final IOException e) {
            LOG.error(La2Config.class.getSimpleName() + ": Error loading " + filename + " config.");
        }
        return result;
    }
}
