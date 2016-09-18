import javax.security.auth.login.ConfigurationSpi;

/**
 * Created by xuch on 8/19/16.
 */
public class MultipleInheritance
{
    public static void main(String[] args)
    {
        Presentable jone = new Presentable()
        {
            @Override
            public void present()
            {
                System.out.println("Here is Jone!");
            }
        };

        jone.present();

        ((Gift) jone).present();
        ((Guest) jone).present();

        Gift joneAsGift = (Gift) jone;
        Guest joneAsGuest = (Guest) jone;
        joneAsGift.present();
        joneAsGuest.present();

        Entrepreneur entrepreneur = new Entrepreneur();
        entrepreneur.handle();
    }
}

interface Gift
{
    void present();
}

interface Guest
{
    void present();
}

interface Presentable extends Gift, Guest {}

// You can inherit methods that are @Override-equivalent, like "void present()" in Gift and Guest.


interface Constitute
{
    void handle();
}

interface Institute
{
    boolean handle();
}

// Entrepreneur can not inherit from both Constitute and Institute, cause Incompatibility
class Entrepreneur implements Constitute//, Institute
{
    @Override
    public void handle()
    {

    }
}