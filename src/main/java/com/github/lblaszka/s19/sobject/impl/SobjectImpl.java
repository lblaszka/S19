package com.github.lblaszka.s19.sobject.impl;

import com.github.lblaszka.s19.sobject.Sobject;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironment;
import com.github.lblaszka.s19.sobjectenvironment.SobjectEnvironmentRepresentative;

public class SobjectImpl implements Sobject
{
    private final long id;
    private final String name;
    private final SobjectStrategy strategy;
    private final SobjectEnvironment environment;


    public static SobjectImpl newInstance( long id, SobjectEnvironment environment, Class strategyClass ) throws IllegalAccessException, InstantiationException
    {
        SobjectStrategy sobjectStrategy = (SobjectStrategy) strategyClass.newInstance();
        return new SobjectImpl( id, sobjectStrategy.name, environment, sobjectStrategy );
    }

    private SobjectImpl( long id, String name, SobjectEnvironment environment, SobjectStrategy strategy )
    {
        this.id = id;
        this.name = name;
        this.environment = environment;
        this.strategy = strategy;
        strategy.setSobject( this );
    }

    @Override
    public long getId()
    {
        return this.id;
    }


    @Override
    public String getName()
    {
        return this.name;
    }


    @Override
    public SobjectStrategy getSobjectStrategy()
    {
        return this.strategy;
    }


    @Override
    public SobjectEnvironmentRepresentative getSobjectEnvironmentRepresentative()
    {
        return this.environment;
    }


    @Override
    public void start()
    {
        this.strategy.start();
    }


    @Override
    public void update()
    {
        this.strategy.update();
    }


    @Override
    public void stop()
    {
        this.strategy.stop();
    }


    @Override
    public void kill()
    {
        this.strategy.kill();
    }
}
