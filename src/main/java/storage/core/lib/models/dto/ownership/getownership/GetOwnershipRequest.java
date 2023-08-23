package storage.core.lib.models.dto.ownership.getownership;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class GetOwnershipRequest extends ChannelMessagePayload {
    private final String address;
    private final String ownershipId;
}
