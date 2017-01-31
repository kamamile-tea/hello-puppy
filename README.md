# hello-puppy
The Puppy Robot is one of the robots that can be built with the LEGO Mindstorms EV3 Education kit.
I wrote a small [leJOS](http://www.lejos.org/) program for it to entertain small children. :)

# How to get it run
You should have a LEGO Mindstorms EV3 brick with leJOS installed and an Eclipse working space where
the leJOS plugin is available (see the [leJOS wiki page](https://sourceforge.net/p/leJOS/wiki/Getting%20started%20with%20leJOS%20EV3/) for details).
1. Build the robot (building instructions can be found in [Fabian Deitelhoff's Blog](http://www.fabiandeitelhoff.de/2015/11/lego-mindstorms-ev3-education-offizielle-bauanleitungen/) (x)
1. Checkout the code and import it into the Eclipse Workspace.
1. Use the EV3control GUI tool that comes with the leJOS eclipse plugin to connect to the brick
and move the head (Motor 3) all the way down.
1. Right-Click on the wav-files in the sound folder and upload them via leJOS EV3 -> Upload...
1. Run the program (Run as -> LeJOS ...)

(x) I am not responsible for the content of this *external* link

# What it does
You can wake the puppy by pressing the touch sensor on the back. When the puppy is awake, you can present the
different colors from the bone (green color is not implemented yet) and the puppy reacts differenty.
After 10s idle time, the puppy sits down and starts to sleep.

# License
General Public License 3, see `LICENSE.txt`.

