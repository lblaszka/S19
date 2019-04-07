package com.github.lblaszka.s19.sobjectcontainer.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobject.SobjectRepresentative;
import com.github.lblaszka.s19.sobjectcontainer.SobjectRepresentativeCollection;

import java.util.*;
import java.util.function.Consumer;

public class SobjectRepresentativeCollectionImpl implements SobjectRepresentativeCollection
{
    private final ArrayList<Sobject> sobjectArrayList;

    public SobjectRepresentativeCollectionImpl( ArrayList<Sobject> sobjectArrayList )
    {
        this.sobjectArrayList = sobjectArrayList;
    }


    @Override
    public SobjectRepresentative getByIndex( int index )
    {
        return this.sobjectArrayList.get( index );
    }


    @Override
    public SobjectRepresentative getById( long id )
    {
        for( Sobject sobject : this.sobjectArrayList )
            if( sobject.getId() == id )
                return sobject;

        return null;
    }


    @Override
    public LinkedList<SobjectRepresentative> getByName( String name )
    {
        LinkedList<SobjectRepresentative> sobjectRepresentativeLinkedList = new LinkedList<>(  );

        for( Sobject sobject : this.sobjectArrayList )
            if( sobject.getName().equals( name ) )
                sobjectRepresentativeLinkedList.add( sobject );

        return sobjectRepresentativeLinkedList;
    }


    @Override
    public Iterator<SobjectRepresentative> iterator()
    {
        return new SobjectRepresentativeIterator();
    }


    @Override
    public void forEach( Consumer<? super SobjectRepresentative> consumer )
    {

    }


    @Override
    public Spliterator<SobjectRepresentative> spliterator()
    {
        return null;
    }

    private class SobjectRepresentativeIterator implements Iterator
    {

        private int count=0;

        @Override
        public boolean hasNext()
        {
            if( count < sobjectArrayList.size() )
                return true;
            return false;
        }


        @Override
        public Object next()
        {
            if( this.hasNext() )
                return sobjectArrayList.get( count++ );
            return null;
        }
    }
}
