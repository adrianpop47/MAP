using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.domain
{
    class JucatorActiv : Entity<int>
    {
        private int idMeci;
        private int nrPuncteInscrise;
        private String tip;


        public int IdMeci { get => idMeci; set => idMeci = value; }
        public int NrPuncteInscrise { get => nrPuncteInscrise; set => nrPuncteInscrise = value; }
        public string Tip { get => tip; set => tip = value; }

        public JucatorActiv(int idJucator, int idMeci, int nrPuncteInscrise, string tip) : base(idJucator)
        {
            this.idMeci = idMeci;
            this.nrPuncteInscrise = nrPuncteInscrise;
            this.tip = tip;
        }

        public override string ToString()
        {
            return ID + " " + idMeci + " " + nrPuncteInscrise + " " + tip;
        }
    }
}
