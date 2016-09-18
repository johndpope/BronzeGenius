package Preference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuch on 7/13/16.
 */
public class PreferenceKeyFactory
{
    //private static final Logger LOGGER = Logger.getLogger(PreferenceKeyFactory.class);

    public enum PreferenceKeyResource {
        ACCOUNTID;
    }

    private static final Map<PreferenceKeyResource, Class> preferenceMap = new HashMap<PreferenceKeyResource, Class>(){
        {
            put(PreferenceKeyResource.ACCOUNTID, SQSPreferenceKey.class);
        }
    };

    public static PreferenceKey getInstance(PreferenceKeyResource preferenceKeyResource, Object preferenceKeyValue) {
        PreferenceKey preferenceKey = null;
        try {
            if (preferenceMap.containsKey(preferenceKeyResource)) {
                Class<?> aClass = preferenceMap.get(preferenceKeyResource);
                Constructor<?> constructor = aClass.getConstructor(preferenceKeyValue.getClass());
                preferenceKey = (PreferenceKey) constructor.newInstance(preferenceKeyValue);
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            //LOGGER.info("Preference with PreferenceKeyResource:[" + preferenceKeyResource + "] and preferenceKeyValue type:[" + preferenceKeyValue.getClass() + "] is not correctly configured.");
            //throw new
        }
        return preferenceKey;
    }


    public static void main(String[] args)
    {
        String keyValue = "acc1";
        PreferenceKey preferenceKey = PreferenceKeyFactory.getInstance(PreferenceKeyResource.ACCOUNTID, keyValue);
        System.out.println(preferenceKey.keyElement);
        preferenceKey.printTest();
    }
}

