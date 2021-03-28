﻿using lab7.domain;
using lab7.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace lab7.service
{
    class EchipaService
    {
        private IRepository<int, Echipa> repo;

        public EchipaService(IRepository<int, Echipa> repo)
        {
            this.repo = repo;
        }


        public List<Echipa> FindAllEchipe()
        {
            return repo.FindAll().ToList();
        }

        public Echipa FindOne(int idEchipa)
        {
            return repo.FindOne(idEchipa);
        }
    }   
}
