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
     * get the count of the supported DMX universes by this device.
     *
     * @return number of supported DMX universes by this device
     */
    public int getSupportedDMXUniverses();

}
