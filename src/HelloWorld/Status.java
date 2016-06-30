package HelloWorld;

/**
 * Created by xuch on 4/28/16.
 */
public enum Status
{
    BeforeArrivedPickup(0), ArrivedPickup(1), Loaded(2), Transporting(3), ArrivedDestination(4), SignedIn(5);
    //LOADING(1) SignOut(6) TransportationFailed

    private int statusFlag;

    Status(int statusFlag)
    {
        this.statusFlag = statusFlag;
    }

    public int getStatusFlag()
    {
        return statusFlag;
    }
}
