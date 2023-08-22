package storage.core.lib.exceptions;

public class ContractInstanceNotFoundException extends Exception {
    public ContractInstanceNotFoundException() {
        super();
    }

    public ContractInstanceNotFoundException(String contractInstanceId) {
        super("The contract instance with id = " + contractInstanceId + " does not exist in the storage");
    }
}
