package com.github.lblaszka.s19.sobjectcontainer.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobjectcontainer.SobjectCollection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class SobjectCollectionImpl implements SobjectCollection
{
    private final ArrayList<Sobject> sobjectArrayList;


    public SobjectCollectionImpl( ArrayList<Sobject> sobjectArrayList )
    {
        this.sobjectArrayList = sobjectArrayList;
    }


    @Override
    public Sobject getByIndex( int index )
    {
        return this.sobjectArrayList.get( index );
    }


    @Override
    public Sobject getById( long id )
    {
        for( Sobject sobject : sobjectArrayList )
        {
            if( sobject.getId() == id )
                return sobject;
        }

        return null;
    }


    @Override
    public Iterator<Sobject> iterator()
    {
        return this.sobjectArrayList.iterator();
    }

    //TODO stg with this!
    @Override
    public void forEach( Consumer<? super Sobject> consumer )
    {

    }


    @Override
    public Spliterator<Sobject> spliterator()
    {
        return this.sobjectArrayList.spliterator();
    }
}
