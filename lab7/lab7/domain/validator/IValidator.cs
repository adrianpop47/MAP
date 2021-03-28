using System;
using System.Collections.Generic;
using System.Text;

namespace lab7.domain.validator
{
    interface IValidator<E>
    {
        void Validate(E e);
    }
}
