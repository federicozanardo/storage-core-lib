package storage.core.lib.module.services;

import lcp.lib.models.assets.Asset;
import storage.core.lib.exceptions.database.DatabaseException;
import storage.core.lib.exceptions.services.asset.AssetNotFoundException;

import java.io.IOException;

public interface IAssetsStorageService {
    Asset getAssetInfo(String assetId) throws IOException, AssetNotFoundException, DatabaseException;
}
