import java.util.*;

public class Main {

    public static List<Student> getList(){
        List<Student> l=new ArrayList<Student>();
        l.add(new Student("1",9.7f));
        l.add(new Student("2",7.3f));
        l.add(new Student("3",6f));
        l.add(new Student("4",6.9f));
        l.add(new Student("5",9.5f));
        l.add(new Student("6",9.9f));return l;
    }

    public static void main(String[] args) {
        Student s1 = new Student("Dan", 4.5f);
        Student s2 = new Student("Ana", 8.5f);
        Student s3 = new Student("Dan", 4.5f);
        Student s4 = new Student("Barbu", 4.5f);
        Set<Student> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList(s1, s2, s3));
//	for(Student st : hashSet)
//	    System.out.println(st);
//    }

        Comparator<Student> compNume = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getNume().compareTo(o2.getNume());
            }
        };

        Collection<Student> treeSet = new TreeSet<>(compNume);
        treeSet.addAll(Arrays.asList(s1, s2, s3, s4));
//		System.out.println(treeSet);

        Map<String, Student> treeMap = new TreeMap<>();
        treeMap.put(s1.getNume(), s1);
        treeMap.put(s2.getNume(), s2);
        treeMap.put(s3.getNume(), s3);
        treeMap.put(s4.getNume(), s4);
//		System.out.println(treeMap);

//		for(Map.Entry<String, Student> pereche : treeMap.entrySet()){
//			System.out.println("Nume: " + pereche.getKey() + " Media: " + pereche.getValue().getMedia());
//		}

        MyMap myMap = new MyMap();
        List<Student> l = getList();
        for(Student s:l)
        {
            myMap.add(s);
        }

        for(var el : myMap.getEntries()) {
            System.out.println("Studentii cu media " + el.getKey() + " sunt:");
            for (var s : el.getValue()) {
                System.out.println(s.getNume() + " ");
            }
        }

    }
}
