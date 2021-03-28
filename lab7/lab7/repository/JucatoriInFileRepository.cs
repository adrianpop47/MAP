using lab7.domain;
using lab7.domain.validator;
using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.repository
{
    class JucatoriInFileRepository : InFileRepository<int, Jucator>
    {
        public JucatoriInFileRepository(IValidator<Jucator> validator, string fileName) : base(validator, fileName, null)
        {
            loadFromFile();
        }

        private new void loadFromFile()
        {
            List<Jucator> jucatori = DataReader.ReadData<Jucator>(fileName, EntityToFileMapping.CreateJucator);
            jucatori.ForEach(x => base.entities[x.ID] = x);
        }
    }
}
