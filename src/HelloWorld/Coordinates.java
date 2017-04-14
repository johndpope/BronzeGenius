package HelloWorld;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xuch.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    private double latitude;
    private double longitude;
}
