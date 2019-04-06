package com.github.lblaszka;

import com.github.lblaszka.s19.sobject.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public void SobjectTest()
    {
        Sobject sobject = new SobjectImpl.Builder()
                .SobjectEnvironmentRepresentative( null )
                .setId( 0 )
                .setName( "NewName" )
                .setStrategy( new MyStrategy() )
                .build();
        sobject.start();
        sobject.update();
        sobject.stop();
    }


    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App app = new App();
        app.SobjectTest();
    }
}

class MyStrategy extends SobjectStrategyImpl
{
    @Override
    public void start()
    {
        System.out.println("My Start!");
    }


    @Override
    public void update()
    {
        System.out.println( "My update!" );
    }


    @Override
    public void stop()
    {
        System.out.println( "My stop!" );
    }
}