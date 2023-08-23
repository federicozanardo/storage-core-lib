package storage.core.lib.models.dto.ownership.getownership;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lcp.lib.models.ownership.Ownership;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class GetOwnershipResponse extends ChannelMessagePayload {
    private final Ownership fund;
}
