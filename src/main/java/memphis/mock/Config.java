package memphis.mock;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Config {

    private String baseFsDir;
    private MockStrategy mockStrategy;
}
