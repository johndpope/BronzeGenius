package HelloWorld;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.joda.time.DateTime;
import java.util.List;
import java.util.ArrayList;


/**
 * @author xuch.
 */
@Data
public class PartyEvent {
    private String name;
    @SerializedName(value = "description", alternate = "DescriptiON")
    private String description;
    private Coordinates coordinates;
    private DateTime eventDate;
    private Location eventLocation;
    private List<String> foodItems;

    public String toString() {
        return name + "," + description + "latitude: " + eventLocation.getLatitude() + ", longitue: " + eventLocation.getLongitude();
    }
}
