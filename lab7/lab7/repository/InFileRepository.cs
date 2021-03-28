using lab7.domain;
using lab7.domain.validator;
using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.repository
{
    public delegate E CreateEntity<E>(string line);
    abstract class InFileRepository<ID, E> : InMemoryRepository<ID, E> where E : Entity<ID>
    {
        protected string fileName;
        protected CreateEntity<E> createEntity;

        public InFileRepository(IValidator<E> validator, String fileName, CreateEntity<E> createEntity) : base(validator)
        {
            this.fileName = fileName;
            this.createEntity = createEntity;
            if (createEntity != null)
                loadFromFile();
        }

        private void loadFromFile()
        {
            List<E> list = DataReader.ReadData(fileName, createEntity);
            list.ForEach(x => entities[x.ID] = x);
        }
    }
}
