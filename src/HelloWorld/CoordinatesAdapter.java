package HelloWorld;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @author xuch.
 */
public class CoordinatesAdapter implements JsonDeserializer<Coordinates> {
    @Override
    public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        if (jsonArray.size() == 2) {
            Coordinates coordinates = new Coordinates();
            coordinates.setLongitude(jsonArray.get(0).getAsDouble());
            coordinates.setLatitude(jsonArray.get(1).getAsDouble());
            return coordinates;
        }
        return null;
    }
}
