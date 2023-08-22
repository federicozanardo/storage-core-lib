package storage.core.lib.module.services;

import lcp.lib.models.assets.Asset;
import storage.core.lib.exceptions.AssetNotFoundException;

import java.io.IOException;

public interface IAssetsStorageService {
    Asset getAssetInfo(String assetId) throws IOException, AssetNotFoundException;
}
