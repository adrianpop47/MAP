using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.domain
{
    class Elev : Entity<int>
    {
        private String nume;
        private String scoala;

        public string Nume { get => nume; set => nume = value; }
        public string Scoala { get => scoala; set => scoala = value; }

        public Elev(int id, string nume, string scoala) : base(id)
        {
            this.nume = nume;
            this.scoala = scoala;
        }

        public override string ToString()
        {
            return ID + " " + Nume + " " + Scoala;
        }
    }
}
