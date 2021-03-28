using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.domain
{
    class Entity<TID>
    {
        private TID id;

        public TID ID { get => id; set => id = value; }

        public Entity(TID id)
        {
            this.id = id;
        }
    }
}
