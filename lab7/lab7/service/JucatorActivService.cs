using lab7.domain;
using lab7.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace lab7.service
{
    class JucatorActivService
    {
        private IRepository<int, JucatorActiv> repo;

        public JucatorActivService(IRepository<int, JucatorActiv> repo)
        {
            this.repo = repo;
        }


        public List<JucatorActiv> FindAllJucatoriActivi()
        {
            return repo.FindAll().ToList();
        }
    }
}
