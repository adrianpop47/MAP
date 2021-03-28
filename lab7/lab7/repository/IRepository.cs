using lab7.domain;
using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.repository
{
    interface IRepository<ID, E> where E : Entity<ID>
    {
        E FindOne(ID id);
        IEnumerable<E> FindAll();
        E Save(E entity);
    }
}
