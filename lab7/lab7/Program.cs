using lab7.console;
using lab7.domain;
using lab7.domain.validator;
using lab7.repository;
using lab7.service;
using System;
using System.Linq;

namespace lab7
{
    class Program
    {
        static void Main(string[] args)
        {
            EchipaValidator echipaValidator = new EchipaValidator();
            EchipeInFileRepository echipeInFileRepository = new EchipeInFileRepository(echipaValidator, "..\\..\\..\\data\\echipe.txt");
            EchipaService echipaService = new EchipaService(echipeInFileRepository);

            JucatorValidator jucatorValidator = new JucatorValidator();
            JucatoriInFileRepository jucatoriInFileRepository = new JucatoriInFileRepository(jucatorValidator, "..\\..\\..\\data\\jucatori.txt");
            JucatorService jucatorService = new JucatorService(jucatoriInFileRepository);

            JucatorActivValidator jucatorActivValidator = new JucatorActivValidator();
            JucatoriActiviInFileRepository jucatoriActiviInFileRepository = new JucatoriActiviInFileRepository(jucatorActivValidator, "..\\..\\..\\data\\jucatoriactivi.txt");
            JucatorActivService jucatorActivService = new JucatorActivService(jucatoriActiviInFileRepository);

            MeciValidator meciValidator = new MeciValidator();
            MeciuriInFileRepository meciuriInFileRepository = new MeciuriInFileRepository(meciValidator, "..\\..\\..\\data\\meciuri.txt");
            MeciService meciService = new MeciService(meciuriInFileRepository);

            Service service = new Service(echipaService, jucatorService, jucatorActivService, meciService);
            UI ui = new UI(service);
            ui.Start();
        }
    }
}
