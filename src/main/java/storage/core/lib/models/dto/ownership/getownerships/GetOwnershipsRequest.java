package storage.core.lib.models.dto.ownership.getownerships;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class GetOwnershipsRequest extends ChannelMessagePayload {
    private final String address;
}
