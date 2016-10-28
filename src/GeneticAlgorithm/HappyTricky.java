package GeneticAlgorithm;

/**
 * Created by xuch on 9/22/16.
 */
public class HappyTricky extends AbstractTricky
{
    @Override
    protected boolean isFree(String absence)
    {
        return false;
    }

    public static void main(String[] args) {
        // call HappyTricky.isFree() no matter the AbstractTricky is abstract or not
        String hello = "";
        String Hello = "";
        AbstractTricky tricky = new HappyTricky();
        System.out.println(((AbstractTricky)tricky).isFree("Absent"));
        tricky.execute("Absent");

        // call AbstractTricky.isFree() when AbstractTricky is not abstract
        tricky = new AbstractTricky();
        System.out.println(tricky.isFree("Absent"));
        tricky.execute("Absent");

    }
}
