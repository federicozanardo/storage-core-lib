package storage.core.lib.models.dto.contractinstance.savestatemachine.function;

import lcp.lib.communication.module.channel.ChannelMessagePayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@ToString
public class SaveStateMachineFromFunctionCallRequest extends ChannelMessagePayload {
    private final String contractInstanceId;
    private final String partyName;
    private final String functionName;
    private final ArrayList<String> argumentsTypes;
}
