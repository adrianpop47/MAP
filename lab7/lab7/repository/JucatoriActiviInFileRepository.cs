using lab7.domain;
using lab7.domain.validator;
using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.repository
{
    class JucatoriActiviInFileRepository : InFileRepository<int, JucatorActiv>
    {
        public JucatoriActiviInFileRepository(IValidator<JucatorActiv> validator, string fileName) : base(validator, fileName, null)
        {
            loadFromFile();
        }

        private new void loadFromFile()
        {
            List<JucatorActiv> jucatoriActivi = DataReader.ReadData<JucatorActiv>(fileName, EntityToFileMapping.CreateJucatorActiv);
            jucatoriActivi.ForEach(x => base.entities[x.ID] = x);
        }
    }
}
