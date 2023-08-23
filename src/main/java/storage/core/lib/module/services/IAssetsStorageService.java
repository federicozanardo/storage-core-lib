package storage.core.lib.module.services;

import lcp.lib.models.assets.Asset;
import storage.core.lib.exceptions.database.DatabaseException;
import storage.core.lib.exceptions.services.asset.AssetNotFoundException;

public interface IAssetsStorageService {

    /**
     * Get the asset information, given an asset id.
     *
     * @param assetId: id of the asset to find in the storage.
     * @return the asset information.
     * @throws AssetNotFoundException: throws when the asset id is not referred to any asset saved in the storage.
     * @throws DatabaseException:      throws when an error occurs while performing a database operation.
     */
    Asset getAssetInfo(String assetId) throws AssetNotFoundException, DatabaseException;
}
