package GeneticAlgorithm;

/**
 * Created by xuch on 9/22/16.
 */
public class AbstractTricky
{
    protected void execute(String absense) {
        System.out.println(isFree(absense));
    }

    protected boolean isFree(String absence) {
        if (absence.equalsIgnoreCase("Absent")) return true;
        else return false;
    }
}
