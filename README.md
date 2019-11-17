# Java Practice

> Java project to practice OOP, inheritance and GUIs.

This project was completed in 2017 for educational purposes. The exercise consisted in the creation of a GUI application that emulates a basic 2D canvas video-game scheme. The canvas has randomy placed moving agents, randomly assigned dot targets, projectiles and interactions between agents and randomly shaped obstacles.

## Running the project

The source code is a **NetBeans IDE** Java Project, and it is recommended to use this environemnt to edit and build the project. To run the code simply:
<br>1. Install the [Apache NetBeans IDE](https://netbeans.apache.org/) (the last performed build was on Netbeans 11.1).<br>
2. Clone this repository on any directory
```
$ git clone https://github.com/pvalls/java-practice
 ```
3. Open the project on NetBeans (File → Open Project)<br>
4. Click the Run Button on NetBeans (▶️)

## Project description and screenshot

The task revolves around the design and implementation of classes following an object oriented programing approach. The main developed tasks are:
* Creating a main window.
* JButton for time steps to update the world state.
* Implementing a shape hierarchy as well as graphics and drawing shape methods.
* Implement entities which can be moving agents or randomly shaped obstacles. Triange agents move and steer towards their target (black dot). When an agent reaches a black dot, it shoots a projectile (blue moving dots) and a new random target is assigned.
* Implementing randomly positioned and shaped obstacles.
* Physical interactions (e.g collisions, agents steer over time in the 2D canvas). Objects turn red when they overlap.

<a href="https://raw.githubusercontent.com/pvalls/java-practice/master/EntityWorldGUI/example_media/Screenshot.png"><img src="https://raw.githubusercontent.com/pvalls/java-practice/master/EntityWorldGUI/example_media/Screenshot.png" title="Java practice screenshot" alt="screenshot" width="400"></a>
</a>
<br>
## Acknowledgments
In colaboration with Víctor Pérez (vipermu).
