using lab7.service;
using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.console
{
    class UI
    {
        private Service service;

        public UI(Service service)
        {
            this.service = service;
        }

        private void PrintAllJucatoriEchipa()
        {
            Console.Write("Introduceti id-ul echipei: ");
            int idEchipa = int.Parse(Console.ReadLine());
            service.PrintAllJucatoriEchipa(idEchipa).ForEach(Console.WriteLine);
            Console.WriteLine("\n");
        }

        private void PrintAllJucatoriActiviMeci()
        {
            Console.Write("Introduceti id-ul echipei: ");
            int idEchipa = int.Parse(Console.ReadLine());
            service.PrintAllMeciuriEchipa(idEchipa).ForEach(Console.WriteLine);

            Console.Write("Introduceti id-ul meciului: ");
            int idMeci = int.Parse(Console.ReadLine());
            service.PrintAllJucatoriActiviMeci(idMeci, idEchipa).ForEach(Console.WriteLine);
            Console.WriteLine("\n");
        }

        private void PrintAllMeciuriPerioada()
        {
            Console.Write("Introduceti data de inceput: ");
            DateTime dataLower = DateTime.Parse(Console.ReadLine());
            Console.Write("Introduceti data finala: ");
            DateTime dataUpper = DateTime.Parse(Console.ReadLine());
            service.PrintAllMeciuriPerioada(dataLower, dataUpper).ForEach(Console.WriteLine);
            Console.WriteLine("\n");
        }

        private void PrintScorMeci()
        {
            Console.Write("Introduceti id-ul meciului: ");
            int idMeci = int.Parse(Console.ReadLine());
            Console.WriteLine(service.PrintScorMeci(idMeci));
            Console.WriteLine("\n");
        }

        public void Start() 
        {
            while (true)
            {
                Console.WriteLine("1. Afiseaza toti jucatorii unei echipe");
                Console.WriteLine("2. Afiseaza toti jucatorii activi ai unei echipe de la un anumit meci");
                Console.WriteLine("3. Afiseaza toate meciurile dintr-o anumita perioada calendaristica");
                Console.WriteLine("4. Afiseaza scorul de la un anumit meci");
                Console.WriteLine("0. exit \n");

                Console.Write("> ");
                string command = Console.ReadLine();
                if (command == "1")
                    PrintAllJucatoriEchipa();
                if (command == "2")
                    PrintAllJucatoriActiviMeci();
                if (command == "3")
                    PrintAllMeciuriPerioada();
                if (command == "4")
                    PrintScorMeci();
                if (command == "exit" || command == "0")
                    return;
            }
        }
    }
}
