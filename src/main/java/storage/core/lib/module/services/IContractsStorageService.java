package storage.core.lib.module.services;

import lcp.lib.models.contract.Contract;
import storage.core.lib.exceptions.database.DatabaseException;
import storage.core.lib.exceptions.services.contract.ContractNotFoundException;

import java.io.IOException;

public interface IContractsStorageService {
    String saveContract(Contract contract) throws IOException, DatabaseException;

    Contract getContract(String contractId) throws IOException, ContractNotFoundException, DatabaseException;
}
