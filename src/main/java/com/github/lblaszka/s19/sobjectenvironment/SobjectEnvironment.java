package com.github.lblaszka.s19.sobjectenvironment;

public interface SobjectEnvironment extends SobjectEnvironmentRepresentative
{
    void start();
    void stop();

    void changeContainer( Class strategyClass );
}
