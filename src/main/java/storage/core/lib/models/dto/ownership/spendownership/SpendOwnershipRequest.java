package storage.core.lib.models.dto.ownership.spendownership;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class SpendOwnershipRequest extends ChannelMessagePayload {
    private final String address;
    private final String ownershipId;
    private final String contractInstanceId;
    private final String unlockScript;
}
