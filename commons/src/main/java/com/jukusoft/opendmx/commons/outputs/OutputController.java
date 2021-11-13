package com.jukusoft.opendmx.commons.outputs;

import java.util.List;
import java.util.Map;

/**
 * the interface for the output controllers (e.q. DMX output or Art-Net).
 *
 * @author Justin Kuenzel
 */
public interface OutputController {

    /**
     * get the unique name of the controller.
     * This is used to find the correct configuration file in data/outputs/<Name>.cfg .
     *
     * @return unique name of the controller
     */
    public String getName();

    /**
     * a generated string which describes the device, e.q. the Manufacturer, Model and the IP address and so an.
     *
     * @return a description string for the device
     */
    public String getDeviceDetails();

    /**
     * get the count of the supported DMX universes by this device.
     *
     * @return number of supported DMX universes by this device
     */
    public int getSupportedDMXUniverses();

    /**
     * check, if the device is connected.
     *
     * @return true, if the device is connected
     */
    public boolean isConnected();

	/**
	 * set values for all DMX channels of one DMX universe.
	 *
	 * @param dmxUniverses dmx universe
	 * @param values the values as byte array
	 */
	public void setValues(int dmxUniverses, byte[] values);

	/**
	 * set the value for a single DMX channel.
	 *
	 * @param dmxUniverse dmx universe
	 * @param dmxAddress dmx adress
	 * @param value the value
	 */
	public void setValue(int dmxUniverse, int dmxAddress, byte value);

	/**
	 * send the current data to the DMX output.
	 */
	public void send();

	/**
	 * get all available config options for the UI.
	 *
	 * @return all config options (map key: category, value: list with options)
	 */
	public Map<String, List<ConfigOption>> getConfigOptions();

	/**
	 * start the controller.
	 */
	public void start();

	/**
	 * shutdown the controller.
	 */
	public void shutdown();

}
