package Guice;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author xuch.
 */
public class SingletonManagerProvider implements Provider<SingletonManager> {

    @Inject
    public SingletonManagerProvider() {

    }

    @Override
    public SingletonManager get() {
        return new SellerManager();
    }
}
