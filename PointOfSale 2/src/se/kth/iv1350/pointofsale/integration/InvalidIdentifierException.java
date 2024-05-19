package se.kth.iv1350.pointofsale.integration;

/**
 * Exception is thrown to indicate that a product
 * does not exist in the inventory catalog
 */
public class InvalidIdentifierException extends Exception {

    public InvalidIdentifierException(String invalidIdentifier) {
        super( invalidIdentifier + " does not exist in the inventory catalog.");
    }

}
