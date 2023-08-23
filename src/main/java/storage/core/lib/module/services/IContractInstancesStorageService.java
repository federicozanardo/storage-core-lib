package storage.core.lib.module.services;

import lcp.lib.models.contract.ContractInstance;
import storage.core.lib.exceptions.database.DatabaseException;
import storage.core.lib.exceptions.services.contractinstance.ContractInstanceNotFoundException;

import java.util.ArrayList;

public interface IContractInstancesStorageService {

    /**
     * Get the contract instance information, given a contract instance id.
     *
     * @param contractInstanceId: id of the contract instance to find in the storage.
     * @return the contract instance information.
     * @throws DatabaseException: throws when an error occurs while performing a database operation.
     */
    ContractInstance getContractInstance(String contractInstanceId) throws ContractInstanceNotFoundException, DatabaseException;

    /**
     * Store a new contract instance.
     *
     * @param contractInstance: the new instance of the contract to save in the storage.
     * @throws DatabaseException: throws when an error occurs while performing a database operation.
     */
    String saveContractInstance(ContractInstance contractInstance) throws DatabaseException;

    // FIXME
    /**
     * This method allows to store the global space in the storage.
     *
     * @param contractInstanceId: id of the contract instance in which store the new global space values.
     * @param updates:            new global space values to store.
     * @throws DatabaseException: throws when an error occurs while performing a database operation.
     */
    /*void saveGlobalSpace(String contractInstanceId, HashMap<String, TraceChange> updates)
            throws IOException,
            ContractInstanceNotFoundException;*/

    /**
     * Store the updates of the state machine when a function has been called.
     *
     * @param contractInstanceId: id of the contract instance to find in the storage.
     * @param partyName:          the party that made the request.
     * @param functionName:       the name of the function called.
     * @param argumentsTypes:     the argument types of the function called.
     * @throws ContractInstanceNotFoundException: throws when the contract instance required does not exist in the storage.
     * @throws DatabaseException:                 throws when an error occurs while performing a database operation.
     */
    void saveStateMachine(String contractInstanceId, String partyName, String functionName, ArrayList<String> argumentsTypes)
            throws ContractInstanceNotFoundException,
            DatabaseException;

    /**
     * Store the updates of the state machine when an obligation function has been called.
     *
     * @param contractInstanceId:     id of the contract instance to find in the storage.
     * @param obligationFunctionName: the name of the obligation function called.
     * @throws ContractInstanceNotFoundException: throws when the contract instance required does not exist in the storage.
     * @throws DatabaseException:                 throws when an error occurs while performing a database operation.
     */
    void saveStateMachine(String contractInstanceId, String obligationFunctionName)
            throws ContractInstanceNotFoundException,
            DatabaseException;
}
