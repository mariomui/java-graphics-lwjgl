# How to set up this project and why?

This project will start from tooling to java primitives to Entity Component System Design.
Seems like there is a lack of bridge between Javascript and JAVA developers as they work together on 3d software systems like AFRAME or ECSY. This repos is baby steps to learn ECS system and how it 
can apply to web development despite not having the ability to access the system for 
performance. JAVA is a similar enough to C++ while at the same time share the limitations of garbage
collection that javascript has. 

## PART1: GRADLE (automated build/run grunt thingie) and JENV (JDK version manager)
* Dont use VSCODE
  * bad auto imports, bad integration with java, use IntelliJ
  * 5 hours vs 30 minutes to get up and running.
* Install gradle directly from the site , dont use homebrew
  * gradle wrapper settings allows you to manually downgrade or (grail versionmanage) yourself.
* jenv //  * java envrionment manager
  * https://github.com/jenv/jenv
    * as of 2021 march, the site has bad cask instructions.
    * please use the export plugin though, its important to automatically pump your JDK version to JAVA_HOME variable. (it works 90% of the time);
    * get the side channels
      * > brew tap homebrew/cask
      * > brew tap homebrew/cask-versions
      * between the two of these casks, you have the top 3 supported versions and any java version you may need.
    * brew install --cask adoptopenjdk
    * jenv add $(/usr/libexec/java_home) ,
      * add the latest installed jSDK to your versions list.
    * brew search --casks jdk
      * search for stuff you want, i install 8, 11, 14
        * gradle does not support v15 of the java sdk.

    * jenv local openjdk64-13.0.2
      * set a file locally to switch you over to the java version of the repos.
    * jenv global openjdk64-13.0.2
      * globally set you to openjdk13.
* jenv doctor to ensure everything is fine.
----

* brew install --cask adoptopenjdk
* brew install --cask adoptopenjdk8
  * jenv add $(/usr/libexec/java_home -v8)
  * place in ~/.zshrc
    * `alias JAVA8="export JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home"`
    * i usually do this for each Java sdk to ensure the global has the correct location of the binary.


------

## Part 2: Intellij and Coding.
* https://github.com/codingminecraft/MarioYoutube/
* Most of the tutorial is from here.
* Keep in mind a few things.
* You need to make sure the Project build settings on the top right corning match the correct JDK.
* Make sure the lower left (build settings) match the correct JDK.
* Click on your Main.java or your entry java and find the button to Build All or Rebuild Project.
* Then press run while still being firmly perusing your entry main.java file.  
* Make sure -XStartFirstThread is in the vm args when you activate your run.
  * https://github.com/LWJGL/lwjgl3-www/issues/81
  * All platforms have an issue where OpenGL and WebGL demand the earliest thread or top thread to run properly. In the mac's case, everything breaks, including java if you dont put the -X arg in.

- [ ] Week 2 Goals
  - [ ] Get familar with basic Object/Array manipulation and primitives as well as coercion differences. 
  - [ ] Optional Nightmare Mode: 
    - [ ] Create a collsion system ping pong battle.
- [ ] Week 3 Goals
  - [ ] Implement an Entity Component System with a lots of global components that needs to be register in entities.
  - [ ] Nightmare: Make an increasingly bizarre list of components and then combine them into different entities. Such as a spray component and a flower component that creates a flower gun. 
- [ ] Week 4 Goals
   - [ ] Study mode, implmeent week2 - 4 in javascript using Mozilla's ecsy system. I would do it in Angular becasue free rxjs.

----

## Versioning

* https://github.com/nebula-plugins/nebula-release-plugin
  * made by netflix
  * prolly the most stable/robust out there as all the others dont support newest versions of gradle.
* how to use
  * bump the major number: ./gradlew <snapshot|devSnapshot|candidate|final> -Prelease.scope=major
  * bump the patch number: ./gradlew <snapshot|devSnapshot|candidate|final> -Prelease.scope=patch
  * `gradle showVersion`
  * caveats
    * you have to do a push after word.
    * improvements need to be on par with my inhouse npm script  but this is good enough for now.