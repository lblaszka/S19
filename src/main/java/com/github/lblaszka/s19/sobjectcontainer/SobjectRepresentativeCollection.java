package com.github.lblaszka.s19.sobjectcontainer;

import com.github.lblaszka.s19.sobject.SobjectRepresentative;

import java.util.LinkedList;

public interface SobjectRepresentativeCollection extends Iterable<SobjectRepresentative>
{
    SobjectRepresentative getByIndex( int index );
    SobjectRepresentative getById( long id );
    LinkedList<SobjectRepresentative> getByName( String name );
}
