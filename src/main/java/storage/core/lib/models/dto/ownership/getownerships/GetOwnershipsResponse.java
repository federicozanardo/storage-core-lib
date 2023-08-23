package storage.core.lib.models.dto.ownership.getownerships;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lcp.lib.models.ownership.Ownership;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@ToString
public class GetOwnershipsResponse extends ChannelMessagePayload {
    private final ArrayList<Ownership> funds;
}
