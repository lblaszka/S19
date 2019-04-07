package com.github.lblaszka.s19.sobjectcontainer.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobject.SobjectImpl;
import com.github.lblaszka.s19.sobjectcontainer.SobjectCollection;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainer;
import com.github.lblaszka.s19.sobjectcontainer.SobjectContainerStrategy;
import com.github.lblaszka.s19.sobjectcontainer.SobjectRepresentativeCollection;

import java.util.ArrayList;

public class SobjectContainerImpl implements SobjectContainer
{
    private long idCount = 0;
    private ArrayList<Sobject> sobjectCollection;
    private SobjectContainerStrategy strategy;

    public static SobjectContainer newInstance( Class strategyClass ) throws IllegalAccessException, InstantiationException
    {
        SobjectContainerStrategy sobjectContainerStrategy = (SobjectContainerStrategy) strategyClass.newInstance();
        return new SobjectContainerImpl(sobjectContainerStrategy);

    }

    private SobjectContainerImpl( SobjectContainerStrategy strategy )
    {
        this.strategy = strategy;
        this.sobjectCollection = new ArrayList<>(  );
    }


    @Override
    public synchronized Exception addSobject( Class sobjectStrategy )
    {
        SobjectImpl sobject;
        try
        {
            sobject = SobjectImpl.newInstance( idCount++, sobjectStrategy );
        } catch ( Exception e )
        {
            e.printStackTrace();
            return e;
        }

        sobject.start();
        this.sobjectCollection.add( sobject );

        return null;
    }


    @Override
    public boolean killSobjectById( long id )
    {
        for( int index = this.sobjectCollection.size() - 1; index >= 0; index-- )
        {
            if( this.sobjectCollection.get( index ).getId() == id )
            {
                this.sobjectCollection.get( index ).stop();
                this.sobjectCollection.get( index ).kill();
                this.sobjectCollection.remove( index );
                return true;
            }
        }

        return false;
    }


    @Override
    public SobjectCollection getSobjectCollection()
    {
        return new SobjectCollectionImpl( this.sobjectCollection );
    }


    @Override
    public void start()
    {
        this.strategy.start();
    }


    @Override
    public void stop()
    {
        for( Sobject sobject: sobjectCollection )
        {
            sobject.stop();
            sobject.kill();
        }

        this.strategy.stop();

    }


    @Override
    public SobjectRepresentativeCollection getSobjectRepresentativeCollection()
    {
        return new SobjectRepresentativeCollectionImpl( this.sobjectCollection );
    }
}
