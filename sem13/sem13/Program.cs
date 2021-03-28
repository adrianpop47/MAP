using System;
using System.Collections.Generic;
using System.Linq;

namespace sem13
{
    class Program
    {
        class Persoana
        {
            public string Nume { set; get; }
            public int Varsta { set; get; }

            public Persoana(string nume, int varsta)
            {
                Nume = nume;
                Varsta = varsta;
            }
        }

        static void Main(string[] args)
        {
            List<Persoana> list1 = new List<Persoana>();
            list1.Add(new Persoana("Andrei", 20));
            list1.Add(new Persoana("Sorin", 12));
            list1.Add(new Persoana("Maria", 18));
            list1.Add(new Persoana("Andreea", 18));
            list1.Add(new Persoana("Cristi", 13));

            List<Tuple<String, int>> list2 = new List<Tuple<String, int>>();
            list2.Add(new Tuple<String, int>("Andrei", 23));
            list2.Add(new Tuple<String, int>("Matei", 33));
            list2.Add(new Tuple<String, int>("Ana", 26));
            list2.Add(new Tuple<String, int>("Maria", 24));
            list2.Add(new Tuple<String, int>("Cristi", 16));

            var finalList = from person1 in list1
                            join person2 in list2 on person1.Nume equals person2.Item1
                            select new Tuple<int, int>(person1.Varsta, person2.Item2);

            finalList
                
                .ToList().ForEach(x => Console.WriteLine(x));
        }
    }
}
