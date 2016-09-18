package Preference;

/**
 * Each account has its channel preference. PreferenceKey can be implemented with any template type.
 * For CCT-CN to SPEC-US SQS, preference key is an account id of "String" type.
 *
 * Created by xuch on 7/13/16.
 */
public class SQSPreferenceKey extends PreferenceKey<String>
{
    public SQSPreferenceKey(String key) {
        keyElement = key;
    }

    @Override
    public void printTest()
    {
        if (this instanceof  SQSPreferenceKey) System.out.println("actually happens");
    }
}
