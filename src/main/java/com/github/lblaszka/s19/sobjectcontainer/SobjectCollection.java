package com.github.lblaszka.s19.sobjectcontainer;

import com.github.lblaszka.s19.sobject.Sobject;

public interface SobjectCollection extends Iterable<Sobject>
{
    Sobject getById( int index );
}
