package HelloWorld;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuch.
 */
public class Constant {
    private static final String DEFAULT_INITIAL_COORDINATES_KEY = "default";

    /**
     * This map stores userId and its initial map location.
     * Anyway, these should be stored in a database in the long term.
     */
    public static final Map<String, Coordinates> INITIAL_COORDINATES_MAPPING = new HashMap<String, Coordinates>() {
        {
            put(DEFAULT_INITIAL_COORDINATES_KEY, new Coordinates(37.76999, -122.44696));
        }
    };

    public static Coordinates getInitialCoordinates(String user) {
        if (INITIAL_COORDINATES_MAPPING.containsKey(user)) {
            return INITIAL_COORDINATES_MAPPING.get(user);
        }
        return getDefaultInitialCoordinates();
    }

    public static Coordinates getDefaultInitialCoordinates() {
        return INITIAL_COORDINATES_MAPPING.get(DEFAULT_INITIAL_COORDINATES_KEY);
    }
}


/* var mapId = 'map';
      var location = {lat: -33.865427, lng: 151.196123};

      $(document).ready(function() { */
       /*
        $.get("/mobilefoodfacilities", function(data, status) {
            if (status === 'success') {
               var result = JSON.parse(data);
               for (var i = 0; i < result.length; i++) {
                 var exhibitionEvent = result[i];
                 var location = {lat: exhibitionEvent.latitude, lng: exhibitionEvent.longitude};
                 var label = exhibitionEvent.eventCode.charAt(0);
                 var info =
                 '<div id="content">' +
                    '<h1 id="firstHeading" class="firstHeading">' + exhibitionEvent.eventCode + '</h1>' +
                    '<div id="bodyContent">' +
                        '<p><b>' + exhibitionEvent.eventName + '</b>:<br>' + exhibitionEvent.description + '</p>' +
                    '</div>' +
                 '</div>';
                 addMarker(location, map, label, info);
               }
            } else {
              alert("Cannot access to the server to retrive data");
            }
          }, "json");
         */

         /*$.get("/initiallocation", function(result, status) {
            location = {lat: result.coordinates.latitude, lng: result.coordinates.longitude};
         }, "json");
         map = new google.maps.Map(document.getElementById('map'), {
            zoom: 2,
            center: location,
            mapTypeId: 'terrain',
         });


      });*/