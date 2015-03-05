/**
 * 
 */
package com.baidu.cloud.channel.model;

/**
 * @author llv23 for status of Deployment
 */
public enum DeployStatus {
	development(1), production(2);

	private final int value;

	private DeployStatus(final int newValue) {
		value = newValue;
	}

	public int getValue() {
		return value;
	}
}
