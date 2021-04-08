# Time

## ticks

the whole point is to get the change in time for each 
loop.

So we need to store start time and the end time per loop

* Supposedly static variables are loaded 
and created when classLoader loads a class. No constructor
  or anything. Java is weird.
  
* We set our starttime here. 

* We get the current time, which is the old new Date - old Date pattern only with float math and in seconds
    getTime(); 10s 13s 14 s etc etc
  
* Now we want to output the dt.

* so go to our loop
    * before the loop, start and end time are 10s
    * dt = 0 at the start.
    * at the end of the loop, change endTime to the currentTime which we calculate in the loop.
    * so now 18s - 10s = 8s our dt.  
    * we then shove the entime as the start time for the next loop.
    
* create a fps ouptutter function that sits out teh 1/dt per loop.
* during our init phase, we change the Scene to 0;
* Why? who knows.
* when we change the scen to 0, we intantiate currentScene for use in our loop.
    * so now our loop can call update(dt);
    * It can change the color depending on what scene you want.