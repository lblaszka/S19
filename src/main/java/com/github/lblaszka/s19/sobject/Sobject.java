package com.github.lblaszka.s19.sobject;

public interface Sobject extends SobjectRepresentative
{
    void start();
    void update();
    void stop();
    void kill();
}
