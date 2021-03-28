using lab7.domain;
using lab7.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace lab7.service
{
    class JucatorService
    {
        private IRepository<int, Jucator> repo;

        public JucatorService(IRepository<int, Jucator> repo)
        {
            this.repo = repo;
        }


        public List<Jucator> FindAllJucatori()
        {
            return repo.FindAll().ToList();
        }

        public Jucator FindOne(int idJucator)
        {
            return repo.FindOne(idJucator);
        }
    }
}
