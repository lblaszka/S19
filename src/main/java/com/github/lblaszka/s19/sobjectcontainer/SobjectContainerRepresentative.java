package com.github.lblaszka.s19.sobjectcontainer;

public interface SobjectContainerRepresentative
{
    Exception addSobject( Class sobjectStrategy );
    boolean killSobjectById( long id );

    SobjectRepresentativeCollection getSobjectRepresentativeCollection();
}
