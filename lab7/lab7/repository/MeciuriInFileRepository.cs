using lab7.domain;
using lab7.domain.validator;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace lab7.repository
{
    class MeciuriInFileRepository : InFileRepository<int, Meci>
    {
        public MeciuriInFileRepository(IValidator<Meci> validator, string fileName) : base(validator, fileName, null)
        {
            loadFromFile();
        }

        private new void loadFromFile()
        {
            List<Meci> meciuri = DataReader.ReadData<Meci>(fileName, EntityToFileMapping.CreateMeci);
            meciuri.ForEach(x => base.entities[x.ID] = x);
        }
    }
}
