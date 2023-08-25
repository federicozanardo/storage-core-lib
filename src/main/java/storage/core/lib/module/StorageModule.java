package storage.core.lib.module;

import lcp.lib.communication.module.Module;
import lcp.lib.communication.module.channel.ChannelMessage;
import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lcp.lib.communication.module.channel.ModuleChannel;
import lcp.lib.communication.module.channel.responses.RequestNotFound;
import lcp.lib.models.assets.Asset;
import lcp.lib.models.contract.Contract;
import lcp.lib.models.contract.ContractInstance;
import lcp.lib.models.ownership.Ownership;
import lcp.lib.models.singleuseseal.SingleUseSeal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storage.core.lib.exceptions.database.DatabaseException;
import storage.core.lib.exceptions.services.asset.AssetNotFoundException;
import storage.core.lib.exceptions.services.contract.ContractNotFoundException;
import storage.core.lib.exceptions.services.contractinstance.ContractInstanceNotFoundException;
import storage.core.lib.exceptions.services.ownership.OwnershipNotFoundException;
import storage.core.lib.exceptions.services.ownership.OwnershipsNotFoundException;
import storage.core.lib.models.dto.asset.getassetinfo.GetAssetInfoRequest;
import storage.core.lib.models.dto.asset.getassetinfo.GetAssetInfoResponse;
import storage.core.lib.models.dto.contract.getcontract.GetContractRequest;
import storage.core.lib.models.dto.contract.getcontract.GetContractResponse;
import storage.core.lib.models.dto.contract.savecontract.SaveContractRequest;
import storage.core.lib.models.dto.contract.savecontract.SaveContractResponse;
import storage.core.lib.models.dto.contractinstance.getcontractinstance.GetContractInstanceRequest;
import storage.core.lib.models.dto.contractinstance.getcontractinstance.GetContractInstanceResponse;
import storage.core.lib.models.dto.contractinstance.savecontractinstance.SaveContractInstanceRequest;
import storage.core.lib.models.dto.contractinstance.savecontractinstance.SaveContractInstanceResponse;
import storage.core.lib.models.dto.contractinstance.savestatemachine.function.SaveStateMachineFromFunctionCallRequest;
import storage.core.lib.models.dto.contractinstance.savestatemachine.function.SaveStateMachineFromFunctionCallResponse;
import storage.core.lib.models.dto.contractinstance.savestatemachine.obligation.SaveStateMachineFromObligationCallRequest;
import storage.core.lib.models.dto.contractinstance.savestatemachine.obligation.SaveStateMachineFromObligationCallResponse;
import storage.core.lib.models.dto.ownership.addownerships.AddOwnershipsRequest;
import storage.core.lib.models.dto.ownership.addownerships.AddOwnershipsResponse;
import storage.core.lib.models.dto.ownership.getownership.GetOwnershipRequest;
import storage.core.lib.models.dto.ownership.getownership.GetOwnershipResponse;
import storage.core.lib.models.dto.ownership.getownerships.GetOwnershipsRequest;
import storage.core.lib.models.dto.ownership.getownerships.GetOwnershipsResponse;
import storage.core.lib.models.dto.ownership.spendownership.SpendOwnershipRequest;
import storage.core.lib.models.dto.ownership.spendownership.SpendOwnershipResponse;
import storage.core.lib.module.services.IAssetsStorageService;
import storage.core.lib.module.services.IContractInstancesStorageService;
import storage.core.lib.module.services.IContractsStorageService;
import storage.core.lib.module.services.IOwnershipsStorageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StorageModule extends Module {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IAssetsStorageService assetsStorageService;
    private final IContractInstancesStorageService contractInstancesStorageService;
    private final IContractsStorageService contractsStorageService;
    private final IOwnershipsStorageService ownershipsStorageService;

    public StorageModule(
            IAssetsStorageService assetsStorageService,
            IContractInstancesStorageService contractInstancesStorageService,
            IContractsStorageService contractsStorageService,
            IOwnershipsStorageService ownershipsStorageService
    ) {
        this.assetsStorageService = assetsStorageService;
        this.contractInstancesStorageService = contractInstancesStorageService;
        this.contractsStorageService = contractsStorageService;
        this.ownershipsStorageService = ownershipsStorageService;
    }

    @Override
    public void receive(ChannelMessage message) {
        logger.debug("[{}] from: {}, payload: {}", new Object() {
        }.getClass().getEnclosingMethod().getName(), message.getSenderModuleId(), message.getPayload());
    }

    @Override
    public ChannelMessage receiveAndResponse(ChannelMessage message) {
        logger.debug("[{}] from: {}, payload: {}", new Object() {
        }.getClass().getEnclosingMethod().getName(), message.getSenderModuleId(), message.getPayload());
        ChannelMessagePayload payload = message.getPayload();

        ChannelMessage response;

        response = assetStorageRouter(payload);

        if (response != null) {
            return response;
        }

        response = contractStorageRouter(payload);

        if (response != null) {
            return response;
        }

        response = contractInstancesStorageRouter(payload);

        if (response != null) {
            return response;
        }

        response = ownershipStorageRouter(payload);

        if (response != null) {
            return response;
        } else {
            return new ChannelMessage(this.getId(), new RequestNotFound(message.getPayload().getClass().getSimpleName()));
        }
    }

    private ChannelMessage assetStorageRouter(ChannelMessagePayload payload) {
        if (payload instanceof GetAssetInfoRequest) {
            try {
                Asset assetInfo = assetsStorageService.getAssetInfo(((GetAssetInfoRequest) payload).getAssetId());
                return new ChannelMessage(this.getId(), new GetAssetInfoResponse(assetInfo));
            } catch (AssetNotFoundException | DatabaseException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    private ChannelMessage contractStorageRouter(ChannelMessagePayload payload) {
        if (payload instanceof SaveContractRequest) {
            Contract contractToSave = ((SaveContractRequest) payload).getContract();

            try {
                String contractId = contractsStorageService.saveContract(contractToSave);
                return new ChannelMessage(this.getId(), new SaveContractResponse(contractId));
            } catch (DatabaseException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        } else if (payload instanceof GetContractRequest) {
            String contractId = ((GetContractRequest) payload).getContractId();

            try {
                Contract contract = contractsStorageService.getContract(contractId);
                return new ChannelMessage(this.getId(), new GetContractResponse(contract));
            } catch (ContractNotFoundException | DatabaseException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    private ChannelMessage contractInstancesStorageRouter(ChannelMessagePayload payload) {
        if (payload instanceof SaveContractInstanceRequest) {
            ContractInstance contractInstance = ((SaveContractInstanceRequest) payload).getContractInstance();

            try {
                String contractInstanceId = contractInstancesStorageService.saveContractInstance(contractInstance);
                return new ChannelMessage(this.getId(), new SaveContractInstanceResponse(contractInstanceId));
            } catch (DatabaseException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        } else if (payload instanceof GetContractInstanceRequest) {
            String contractInstanceId = ((GetContractInstanceRequest) payload).getContractInstanceId();

            try {
                ContractInstance contractInstance = contractInstancesStorageService.getContractInstance(contractInstanceId);
                return new ChannelMessage(this.getId(), new GetContractInstanceResponse(contractInstance));
            } catch (ContractInstanceNotFoundException | DatabaseException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        } else if (payload instanceof SaveStateMachineFromFunctionCallRequest) {
            String contractInstanceId = ((SaveStateMachineFromFunctionCallRequest) payload).getContractInstanceId();
            String partyName = ((SaveStateMachineFromFunctionCallRequest) payload).getPartyName();
            String functionName = ((SaveStateMachineFromFunctionCallRequest) payload).getFunctionName();
            ArrayList<String> argumentsTypes = ((SaveStateMachineFromFunctionCallRequest) payload).getArgumentsTypes();

            try {
                contractInstancesStorageService.saveStateMachine(contractInstanceId, partyName, functionName, argumentsTypes);
                return new ChannelMessage(this.getId(), new SaveStateMachineFromFunctionCallResponse());
            } catch (ContractInstanceNotFoundException | DatabaseException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        } else if (payload instanceof SaveStateMachineFromObligationCallRequest) {
            String contractInstanceId = ((SaveStateMachineFromObligationCallRequest) payload).getContractInstanceId();
            String obligationFunctionName = ((SaveStateMachineFromObligationCallRequest) payload).getObligationFunctionName();

            try {
                contractInstancesStorageService.saveStateMachine(contractInstanceId, obligationFunctionName);
                return new ChannelMessage(this.getId(), new SaveStateMachineFromObligationCallResponse());
            } catch (ContractInstanceNotFoundException | DatabaseException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        }

        return null;
    }

    private ChannelMessage ownershipStorageRouter(ChannelMessagePayload payload) {
        if (payload instanceof GetOwnershipRequest) {
            String address = ((GetOwnershipRequest) payload).getAddress();
            String ownershipId = ((GetOwnershipRequest) payload).getOwnershipId();

            try {
                Ownership fund = ownershipsStorageService.getOwnership(address, ownershipId);
                return new ChannelMessage(this.getId(), new GetOwnershipResponse(fund));
            } catch (OwnershipsNotFoundException | OwnershipNotFoundException | DatabaseException | IOException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        } else if (payload instanceof GetOwnershipsRequest) {
            String address = ((GetOwnershipsRequest) payload).getAddress();

            try {
                ArrayList<Ownership> funds = ownershipsStorageService.getOwnerships(address);
                return new ChannelMessage(this.getId(), new GetOwnershipsResponse(funds));
            } catch (OwnershipsNotFoundException | DatabaseException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        } else if (payload instanceof AddOwnershipsRequest) {
            HashMap<String, SingleUseSeal> funds = ((AddOwnershipsRequest) payload).getFunds();

            try {
                ownershipsStorageService.addOwnerships(funds);
                return new ChannelMessage(this.getId(), new AddOwnershipsResponse());
            } catch (DatabaseException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        } else if (payload instanceof SpendOwnershipRequest) {
            String address = ((SpendOwnershipRequest) payload).getAddress();
            String ownershipId = ((SpendOwnershipRequest) payload).getOwnershipId();
            String contractInstanceId = ((SpendOwnershipRequest) payload).getContractInstanceId();
            String unlockScript = ((SpendOwnershipRequest) payload).getUnlockScript();

            try {
                ownershipsStorageService.spendOwnership(address, ownershipId, contractInstanceId, unlockScript);
                return new ChannelMessage(this.getId(), new SpendOwnershipResponse());
            } catch (OwnershipsNotFoundException | OwnershipNotFoundException | DatabaseException | IOException e) {
                // TODO: handle it
                throw new RuntimeException(e);
            }
        }

        return null;
    }
}
