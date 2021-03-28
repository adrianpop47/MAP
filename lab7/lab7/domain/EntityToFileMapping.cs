using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.domain
{
    class EntityToFileMapping
    {
        public static Echipa CreateEchipa(string line)
        {
            string[] fields = line.Split(','); // new char[] { ',' } 
            Echipa echipa = new Echipa(int.Parse(fields[0]), fields[1]);
            return echipa;
        }

        public static Jucator CreateJucator(string line)
        {
            string[] fields = line.Split(',');
            Jucator jucator = new Jucator(int.Parse(fields[0]), fields[1], fields[2], int.Parse(fields[3]));
            return jucator;
        }

        public static Meci CreateMeci(string line)
        {
            string[] fields = line.Split(',');
            Meci meci = new Meci(int.Parse(fields[0]), int.Parse(fields[1]), int.Parse(fields[2]), DateTime.Parse(fields[3]));
            return meci;
        }

        public static JucatorActiv CreateJucatorActiv(string line)
        {
            string[] fields = line.Split(',');
            JucatorActiv jucatorActiv = new JucatorActiv(int.Parse(fields[0]), int.Parse(fields[1]), int.Parse(fields[2]), fields[3]);
            return jucatorActiv;
        }
    }
}
