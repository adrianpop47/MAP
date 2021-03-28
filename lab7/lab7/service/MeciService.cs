using lab7.domain;
using lab7.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace lab7.service
{
    class MeciService
    {
        private IRepository<int, Meci> repo;

        public MeciService(IRepository<int, Meci> repo)
        {
            this.repo = repo;
        }


        public List<Meci> FindAllMeciuri()
        {
            return repo.FindAll().ToList();
        }

        public Meci FindOne(int idMeci)
        {
            return repo.FindOne(idMeci);
        }
    }
}
