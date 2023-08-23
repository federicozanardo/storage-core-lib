package storage.core.lib.exceptions.services.asset;

public class AssetNotFoundException extends Exception {
    public AssetNotFoundException() {
        super();
    }

    public AssetNotFoundException(String assetId) {
        super("The asset with id = " + assetId + " does not exist in the storage");
    }
}
