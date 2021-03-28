using lab7.domain;
using lab7.domain.validator;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace lab7.repository
{
    class EchipeInFileRepository : InFileRepository<int, Echipa>
    {
        public EchipeInFileRepository(IValidator<Echipa> validator, string fileName) : base(validator, fileName, null)
        {
            loadFromFile();
        }

        private new void loadFromFile()
        {
            List<Echipa> echipe = DataReader.ReadData<Echipa>(fileName, EntityToFileMapping.CreateEchipa);
            echipe.ForEach(x => base.entities[x.ID] = x);
        }
    }
}
