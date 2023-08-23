package storage.core.lib.module.services;

import lcp.lib.models.contract.Contract;
import storage.core.lib.exceptions.database.DatabaseException;
import storage.core.lib.exceptions.services.contract.ContractNotFoundException;

public interface IContractsStorageService {

    /**
     * Get the contract information, given a contract id.
     *
     * @param contractId: id of the contract to find in the storage.
     * @return the contract information.
     * @throws ContractNotFoundException: throws when the contract id is not referred to any contract saved in the storage.
     * @throws DatabaseException:         throws when an error occurs while performing a database operation.
     */
    Contract getContract(String contractId) throws ContractNotFoundException, DatabaseException;

    /**
     * Save a new contract in the storage.
     *
     * @param contract: data of the contract to store.
     * @return the id of the contract just saved.
     * @throws DatabaseException: throws when an error occurs while performing a database operation.
     */
    String saveContract(Contract contract) throws DatabaseException;
}
