/**
 * 
 */
package utils;

@SuppressWarnings("serial")
public class DeathException extends Exception {
	public DeathException() {
		super("Character died");
	}
}