package storage.core.lib.module.services;

import lcp.lib.models.contract.ContractInstance;
import storage.core.lib.exceptions.database.DatabaseException;
import storage.core.lib.exceptions.services.contractinstance.ContractInstanceNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

public interface IContractInstancesStorageService {
    ContractInstance getContractInstance(String contractInstanceId) throws IOException, ContractInstanceNotFoundException, DatabaseException;

    String saveContractInstance(ContractInstance contractInstance) throws IOException, DatabaseException;

    // FIXME
    /*void saveGlobalSpace(String contractInstanceId, HashMap<String, TraceChange> updates)
            throws IOException,
            ContractInstanceNotFoundException;*/

    void saveStateMachine(String contractInstanceId, String partyName, String functionName, ArrayList<String> argumentsTypes)
            throws IOException,
            ContractInstanceNotFoundException,
            DatabaseException;

    void saveStateMachine(String contractInstanceId, String obligationFunctionName)
            throws IOException,
            ContractInstanceNotFoundException,
            DatabaseException;
}
