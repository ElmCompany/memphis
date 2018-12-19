package memphis.mock;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Config {

    private final String baseFsDir;
    private final MockStrategy mockStrategy;
}
