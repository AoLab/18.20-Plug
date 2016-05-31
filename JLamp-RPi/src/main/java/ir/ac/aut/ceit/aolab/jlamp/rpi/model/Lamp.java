package ir.ac.aut.ceit.aolab.jlamp.rpi.model;

import ir.ac.aut.ceit.aolab.jlamp.rpi.serial.Serial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by iman on 5/21/16.
 */
public class Lamp {

    private String id;
    private static Serial serial = Serial.getSerialInstance();
    private static final char identifier = 'L';
    private static final Logger LOG = LoggerFactory.getLogger(Lamp.class);
    private static HashMap<String, Lamp> lampDatabase = new HashMap<>();

    public Lamp(String id) {
        this.id = id;
        lampDatabase.put(id, this);
    }

    public void sendLampCommand(int status) {
        String command = identifier + id + status + '\n';
        serial.write(command);
        LOG.info("Sent lamp command " + command);

    }

    public static Lamp getLampById(String id) {
        if(lampDatabase.containsKey(id))
            return lampDatabase.get(id);
        else
            return new Lamp(id);
    }

    public boolean getLampStatus(){
        String command = identifier + id + '2' + '\n';
        serial.write(command);
        LOG.info("Sent lamp command " + command);

        serial.readChar(); // Skipping #
        serial.readChar(); // Skipping (
        boolean status = serial.readChar() == 1 ? true : false;
        serial.readChar(); // Skipping )

        return status;
    }
}