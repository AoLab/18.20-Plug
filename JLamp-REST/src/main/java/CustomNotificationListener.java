import ir.ac.aut.ceit.iot.TimeInterval;
import org.kaaproject.kaa.client.notification.NotificationListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by iman on 4/29/16.
 */
public class CustomNotificationListener implements NotificationListener{

    private final Logger LOG = LoggerFactory.getLogger(CustomNotificationListener.class);

    @Override
    public void onNotification(long topicId, TimeInterval timeInterval) {
        LOG.info("Received notification {} for topic id {}", timeInterval, topicId);
    }
}
