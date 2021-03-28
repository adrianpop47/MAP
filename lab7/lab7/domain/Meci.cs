using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.domain
{
    class Meci : Entity<int>
    {
        private int echipa1;

        private int echipa2;

        private DateTime data;

        public int Echipa1 { get => echipa1; set => echipa1 = value; }
        public int Echipa2 { get => echipa2; set => echipa2 = value; }
        public DateTime Data { get => data; set => data = value; }

        public Meci(int id, int echipa1, int echipa2, DateTime data) : base(id)
        {
            this.echipa1 = echipa1;
            this.echipa2 = echipa2;
            this.data = data;
        }

        public override string ToString()
        {
            return ID + " " + Echipa1 + " " + Echipa2 + " " + Data;
        }
    }
}
