package Preference;


/**
 * Each account has its channel preference. PreferenceKey can be implemented with any template type.
 * For CCT-CN to SPEC-US SQS, preference key is an account id of "String" type.
 *
 * Created by xuch on 7/13/16.
 */
public abstract class PreferenceKey<T>
{
    T keyElement;

    public abstract void printTest();
}
