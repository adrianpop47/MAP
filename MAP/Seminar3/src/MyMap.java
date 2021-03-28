import java.util.*;

public class MyMap {
    Map<Integer, List<Student>> map;

    static class ComparatorMedie implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2){
            return o2.compareTo(o1);
        }

    }

    public MyMap(){
        map = new TreeMap<>();
    }

    public void add(Student s)
    {
        int medie = Math.round(s.getMedia());
        boolean exists=map.containsKey(medie);
        if(exists)
        {
            List<Student> list=map.get(medie);
            list.add(s);
        }
        else
        {
            List<Student> list = new ArrayList<>();
            list.add(s);
            map.put(medie, list);
        }
    }

    public Set<Map.Entry<Integer, List<Student>>> getEntries()
    {
        return map.entrySet();
    }
}
