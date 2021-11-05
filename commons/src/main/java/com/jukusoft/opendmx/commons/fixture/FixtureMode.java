package com.jukusoft.opendmx.commons.fixture;

/*
 * every fixture can have more than one mode, e.q. if there are more DMX modes.
 *
 * @author Justin Kuenzel
 */
public class FixtureMode {

	/**
	 * the dmx channels.
	 */
	private DMXChannel[] dmxChannels;

	/**
	 * the count of the heads (a headlight can be a multihead, this means it can e.q. has 4 heads).
	 */
	private int headCount = 1;

	/**
	 * default constructor.
	 *
	 * @param dmxChannels count of dmx channels of the headlight.
	 * @param headCount the number of heads of the device
	 */
	public FixtureMode(int dmxChannels, int headCount) {
		this.dmxChannels = new DMXChannel[dmxChannels];
		this.headCount = headCount;
	}
}
