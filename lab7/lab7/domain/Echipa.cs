using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.domain
{
    class Echipa : Entity<int>
    {
        private String nume;
        public string Nume { get => nume; set => nume = value; }

        public Echipa(int id, string nume) : base(id)
        {
            this.nume = nume;
        }

        public override string ToString()
        {
            return ID + " " + nume;
        }
    }
}
