package storage.core.lib.models.dto.contractinstance.savestatemachine.obligation;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class SaveStateMachineFromObligationCallRequest extends ChannelMessagePayload {
    private final String contractInstanceId;
    private final String obligationFunctionName;
}
