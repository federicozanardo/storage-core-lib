package storage.core.lib.module.services;

import lcp.lib.models.contract.Contract;
import storage.core.lib.exceptions.ContractNotFoundException;

import java.io.IOException;

public interface IContractsStorageService {
    String saveContract(Contract contract) throws IOException;

    Contract getContract(String contractId) throws IOException, ContractNotFoundException;
}
