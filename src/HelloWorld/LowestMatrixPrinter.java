package HelloWorld;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.*;

public class LowestMatrixPrinter {

    public static void main(String[] args) {


        int cityLength=5;
        int cityWidth=7;
        int[] xCordinates ={2,4};
        int[] yCordinates={3,7};

        int[][] result = printMatrix(cityLength,cityWidth,xCordinates,yCordinates);
        print_(result);

    }

    private static int[][] printMatrix(int cityLength, int cityWidth,
                                    int[] xCordinates, int[] yCordinates) {
        int[][] result = new int[cityWidth][cityLength];
        for(int i=0;i<cityWidth;i++){
            for(int j=0;j<cityLength;j++){
                int lockDistance=findNearestLocker(i,j,xCordinates,yCordinates);
                //System.out.print(lockDistance +" ");
                result[i][j] = lockDistance;
            }
            //System.out.println();
        }

        return result;
    }

    private static int findNearestLocker(int i, int j, int[] xCordinates,
                                         int[] yCordinates) {
        int totalLocker= xCordinates.length;
        int distance=Integer.MAX_VALUE;
        for(int l=0;l<totalLocker;l++){
            int x=xCordinates[l];
            int y =yCordinates[l];
            int tempDistance=Math.abs(x-j-1)+Math.abs(y-i-1);
            if(distance>tempDistance){
                distance=tempDistance;
            }
        }
        return distance;
    }

    private static void print_(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
    }

    public List<String> getDirectFriendsForUser(String user) {
        return new ArrayList<String>();
    }
    
    public List<String> getAttendedCoursesForUser(String user) {
        return new ArrayList<String>();
    }
    

    private List<String> getSocialNetWorkForUser(String user)
    {

        List<String> directFriends = getDirectFriendsForUser(user);
        Set<String> socialNetWork = new LinkedHashSet<String>();
        for (String directFriend : directFriends)
        {
            socialNetWork.add(directFriend);
            List<String> secondFriends = getDirectFriendsForUser(directFriend);
            for (String secondFriend : secondFriends)
            {
                socialNetWork.add(secondFriend);
            }
        }

        return new ArrayList<String>(socialNetWork);
    }
    private List<String> getRecommendedCourses(String user) {
        Map<String, Integer> courseCount = new HashMap<String, Integer>();
        List<String> socialNetWork = getSocialNetWorkForUser(user);
        List<String> courses;
        for (String friend : socialNetWork) {
            courses = getAttendedCoursesForUser(friend);
            for (String course : courses) {
                if (courseCount.containsKey(course))
                    courseCount.put(course, courseCount.get(course) + 1);
                else courseCount.put(course, 0);
            }
        }

        courseCount = sortByValue(courseCount);
        List<String> recommendedCourses = new ArrayList<>(courseCount.keySet());

        return recommendedCourses;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return ( o1.getValue() ).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
}
