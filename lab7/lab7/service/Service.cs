using lab7.domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace lab7.service
{
    class Service
    {
        private EchipaService echipaService;
        private JucatorService jucatorService;
        private JucatorActivService jucatorActivService;
        private MeciService meciService;

        public Service(EchipaService echipaService, JucatorService jucatorService, JucatorActivService jucatorActivService, MeciService meciService)
        {
            this.echipaService = echipaService;
            this.jucatorService = jucatorService;
            this.jucatorActivService = jucatorActivService;
            this.meciService = meciService;
        }

        public List<Jucator> PrintAllJucatoriEchipa(int idEchipa) 
        {
            var res = jucatorService.FindAllJucatori().Where(x => x.Echipa == idEchipa);
            return res.ToList();
        }

        public List<Meci> PrintAllMeciuriEchipa(int idEchipa)
        {
            var res = meciService.FindAllMeciuri().Where(x => x.Echipa1 == idEchipa || x.Echipa2 == idEchipa);
            return res.ToList();
        }

        public List<JucatorActiv> PrintAllJucatoriActiviMeci(int idMeci, int idEchipa)
        {
            var res = jucatorActivService.FindAllJucatoriActivi().Where(x => x.IdMeci == idMeci).Where(x => jucatorService.FindOne(x.ID).Echipa == idEchipa);
            return res.ToList();
        }

        public List<Meci> PrintAllMeciuriPerioada(DateTime dataLower, DateTime dataUpper)
        {
            var res = meciService.FindAllMeciuri().Where(x => x.Data.Date >= dataLower && x.Data.Date <= dataUpper);
            return res.ToList();
        }

        public string PrintScorMeci(int idMeci)
        {
            int idEchipa1 = meciService.FindOne(idMeci).Echipa1;
            int idEchipa2 = meciService.FindOne(idMeci).Echipa2;
            var scorEchipa1 = PrintAllJucatoriActiviMeci(idMeci,idEchipa1).Sum(x => x.NrPuncteInscrise);
            var scorEchipa2 = PrintAllJucatoriActiviMeci(idMeci, idEchipa2).Sum(x => x.NrPuncteInscrise);
            string echipe = echipaService.FindOne(idEchipa1).Nume + "-" + echipaService.FindOne(idEchipa2).Nume;
            string scor = scorEchipa1 + "-" + scorEchipa2;
            return echipe + "\n" + scor;
        }
    }
}
