package com.jukusoft.opendmx.outputs.controller;

import ch.bildspur.artnet.ArtNetClient;
import ch.bildspur.artnet.ArtNetNodeDiscovery;
import com.jukusoft.opendmx.commons.outputs.ConfigOption;
import com.jukusoft.opendmx.commons.outputs.OutputController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This controller adds support for Art-Net nodes and represents a single Art-Net node.
 *
 * @author Justin Kuenzel
 */
public class ArtNetController implements OutputController {

	/**
	 * the logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ArtNetController.class);

	/**
	 * the art-net client.
	 */
	private ArtNetClient client;

	/**
	 * the map with the dmx data.
	 */
	private Map<Integer, byte[]> dmxData = new HashMap<>(4);

	/**
	 * the IP address of the art-net server.
	 */
	private String ipAddress;

	/**
	 * the port of the art-net server.
	 */
	private int port;

	/**
	 * the subnet.
	 */
	private int subNet = 0;

	/**
	 * flag, if the controller is in broadcast mode.
	 */
	private boolean broadcast = false;

	/**
	 * default constructor.
	 */
	public ArtNetController() {
		this.client = new ArtNetClient();
	}

	@Override
	public String getName() {
		return "Art-Net (artnet4j)";
	}

	@Override
	public String getDeviceDetails() {
		return "Art-Net node " + ipAddress + ":" + port;
	}

	@Override
	public int getSupportedDMXUniverses() {
		return 64;
	}

	@Override
	public boolean isConnected() {
		return this.client.isRunning();
	}

	@Override
	public void setValues(int dmxUniverse, byte[] values) {
		this.dmxData.put(dmxUniverse, values);
	}

	@Override
	public void setValue(int dmxUniverse, int dmxAddress, byte value) {
		if (!this.dmxData.containsKey(dmxUniverse)) {
			LOGGER.warn("Cannot set value for DMX channel {}:{} (value: {}), because the universe is not configured on this controller.", dmxUniverse, dmxAddress, value);
			return;
		}

		this.dmxData.get(dmxUniverse)[dmxAddress] = value;
	}

	@Override
	public void send() {
		if (!this.client.isRunning()) {
			LOGGER.warn("Cannot send DMX data to art-net node '{}:{}', because ArtNetClient was not started yet.", ipAddress, port);
			return;
		}

		//iterate through all DMX universes
		for (Map.Entry<Integer, byte[]> entry : dmxData.entrySet()) {
			int dmxUniverse = entry.getKey();
			byte[] dmxData = entry.getValue();

			if (!this.broadcast) {
				this.client.unicastDmx(ipAddress, subNet, dmxUniverse, dmxData);
			} else {
				this.client.broadcastDmx(subNet, dmxUniverse, dmxData);
			}
		}
	}

	@Override
	public Map<String, List<ConfigOption>> getConfigOptions() {
		Map<String, List<ConfigOption>> configs = new HashMap<>();

		//general
		List<ConfigOption> generalOptions = new ArrayList<>();
		generalOptions.add(new ConfigOption("ip", "Art-Net Node IP Address", ConfigOption.CONFIG_TYPE.STRING));
		generalOptions.add(new ConfigOption("port", "Art-Net Node Port", ConfigOption.CONFIG_TYPE.INTEGER));
		generalOptions.add(new ConfigOption("subnet", "Art-Net Node Subnet (default: 0)", ConfigOption.CONFIG_TYPE.INTEGER));
		generalOptions.add(new ConfigOption("broadcast", "Broadcast Mode (else unicast)", ConfigOption.CONFIG_TYPE.BOOLEAN));
		generalOptions.add(new ConfigOption("dmxUniverses", "Number of dmx universes (default: 1)", ConfigOption.CONFIG_TYPE.INTEGER));

		configs.put("General", generalOptions);

		return configs;
	}

	@Override
	public void start() {
		this.client.start();
	}

	@Override
	public void shutdown() {
		this.client.stop();
	}

}
