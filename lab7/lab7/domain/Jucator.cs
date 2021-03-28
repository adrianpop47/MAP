using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.domain
{
    class Jucator : Elev
    {
        private int echipa;
        public int Echipa { get => echipa; set => echipa = value; }

        public Jucator(int id,string nume, string scoala, int echipa) : base(id, nume, scoala)
        {
            this.echipa = echipa;
        }

        public override string ToString()
        {
            return base.ToString() + " " + Echipa;
        }
    }
}
