package com.jukusoft.opendmx.commons.outputs;

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

}
