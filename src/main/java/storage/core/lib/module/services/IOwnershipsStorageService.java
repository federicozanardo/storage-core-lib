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

    /**
     * This method allows to add new funds.
     *
     * @param ownerships: the funds to be stored.
     * @throws DatabaseException: throws when an error occurs while performing a database operation.
     */
    void addOwnerships(HashMap<String, SingleUseSeal> ownerships) throws DatabaseException;

    /**
     * This method allows to get the funds associated to a given address.
     *
     * @param address: it is needed in order to search the funds associated.
     * @return the funds associated to the address.
     * @throws OwnershipsNotFoundException : throws when there are no funds associated to the given address.
     * @throws DatabaseException:          throws when an error occurs while performing a database operation.
     */
    ArrayList<Ownership> getOwnerships(String address) throws OwnershipsNotFoundException, DatabaseException;

    /**
     * This method allows to get a specific ownership, given an address.
     *
     * @param address:     the address associated to the ownership to obtain.
     * @param ownershipId: the id of the specific ownership to obtain.
     * @return the fund associated to the address.
     * @throws OwnershipsNotFoundException: throws when there are no funds associated to the given address.
     * @throws OwnershipNotFoundException:  throws when the ownership id is not referred to the given address or to any ownership saved in the storage.
     * @throws DatabaseException:           throws when an error occurs while performing a database operation.
     */
    Ownership getOwnership(String address, String ownershipId)
            throws IOException,
            OwnershipsNotFoundException,
            OwnershipNotFoundException,
            DatabaseException;

    /**
     * This method allows to make spent an ownership.
     *
     * @param address:            the address associated to the ownership to make spent.
     * @param ownershipId:        the id of the specific ownership to make spent.
     * @param contractInstanceId: id of the contract instance to find in the storage.
     * @param unlockScript:       the first part of the script that can prove the spendability of the ownership.
     * @throws OwnershipsNotFoundException: throws when there are no funds associated to the given address.
     * @throws OwnershipNotFoundException:  throws when the ownership id is not referred to the given address or to any ownership saved in the storage.
     * @throws DatabaseException:           throws when an error occurs while performing a database operation.
     */
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
