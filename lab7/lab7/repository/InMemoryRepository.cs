using lab7.domain;
using lab7.domain.validator;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace lab7.repository
{
    class InMemoryRepository<ID, E> : IRepository<ID, E> where E : Entity<ID>
    {
        protected IValidator<E> validator;

        protected IDictionary<ID, E> entities = new Dictionary<ID, E>();

        public InMemoryRepository(IValidator<E> validator)
        {
            this.validator = validator;
        }

        public IEnumerable<E> FindAll()
        {
            return entities.Values.ToList<E>();
        }

        public E FindOne(ID id)
        {
            return entities[id];
        }

        public E Save(E entity)
        {
            if (entity == null)
                throw new ArgumentNullException("entity must not be null");
            this.validator.Validate(entity);
            if (this.entities.ContainsKey(entity.ID))
            {
                return entity;
            }
            this.entities[entity.ID] = entity;
            return default(E);
        }
    }
}
