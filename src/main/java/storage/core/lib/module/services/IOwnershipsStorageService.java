package storage.core.lib.module.services;

import lcp.lib.models.ownership.Ownership;
import lcp.lib.models.singleuseseal.SingleUseSeal;
import storage.core.lib.exceptions.database.DatabaseException;
import storage.core.lib.exceptions.services.ownership.OwnershipNotFoundException;
import storage.core.lib.exceptions.services.ownership.OwnershipsNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface IOwnershipsStorageService {
    void addOwnerships(HashMap<String, SingleUseSeal> funds) throws IOException, DatabaseException;

    ArrayList<Ownership> getOwnerships(String address) throws IOException, OwnershipsNotFoundException, DatabaseException;

    Ownership getOwnership(String address, String ownershipId)
            throws IOException,
            OwnershipsNotFoundException,
            OwnershipNotFoundException,
            DatabaseException;

    void spendOwnership(
            String address,
            String ownershipId,
            String contractInstanceId,
            String unlockScript
    ) throws IOException,
            OwnershipsNotFoundException,
            OwnershipNotFoundException,
            DatabaseException;
}
