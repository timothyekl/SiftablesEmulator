This project is a reasonably small simulator for Sifteo Cubes,
1.5-inch minicomputers that provide a novel new gameplay platform.
At present, no official SDK is available for the cubes, and so
this project aims to provide a testbed for game ideas and concepts
while showing the hardware capabilities of the cubes.

It's important to note two things:

* Games developed for this platform **will not** transfer to the
  actual cubes or official SDK, when available. (That SDK may not
  even be in Java, let alone compatible with the interface exposed
  here.)
* This project has **no affiliation** with Sifteo or the developers
  of the cubes. It is **entirely unofficial**, and bugs are the
  fault of the simulator, not Sifteo's.

Finally, this simulator was originally done as an academic teaching
tool for the Java language - it is not necessarily complete with
respect to feature availability. The important part of this
simulator is the ability to modify it, extend it, and see what's
possible, learning all the while.

That said, let's get to it!

### Developing a game
Game development is simple - just add a class to the
`com.lithium3141.SifteoCubes.Games` package that implements the
`Game` interface. This interface contains all the methods that
fully define a game's interaction with the simulator, including
some required info about the game (number of cubes, title) and
notification methods for the game to handle cube actions.

Two particularly important methods are `load()` and `unload()`;
while the rest are somewhat self-explanatory, these two often
bear the brunt of the work without much description. In actuality,
all they do is handle the initial startup and final shutdown of
the game. When a game is first selected, its `load()` method is
called; this method is responsible for initializing any game
private variables that are needed, as well as for handling cubes
already in play. Similarly, the `unload()` method should release
unneeded information and prepare to give up control of the
simulator to another game.

One last step: making a game show up in the main simulator menu
requires a single extra line of code in the Emulator class itself,
calling the `registerGame(Game g)` method with a new instance of
the new game.

Lastly, don't forget that games can call the `Emulator.getCubes()`
method to get a list of cubes in play at any time!

### Extending the simulator
The simulator and its classes are all contained in the
`com.lithium3141.SifteoCubes` package, and are responsible not
for gameplay but for the basic mechanics of the system - movement,
rotations, collisions, and the like. The simulator also sends
the relevant notifications to games when cube actions occur.

When changing the simulator, be careful not to affect any
functionality already in place! Some basic tests are available in
`com.lithium3141.SifteoCubes.Tests`, but it's usually good practice
to try games by hand that may have been affected.

Pull requests that change the simulator will be examined much more
closely than requests that simply add a new game.

### Legal stuff
This project is distributed under the BSD license. Full terms follow.

Copyright (c) 2011, Tim Ekl
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
* Neither the name of the <ORGANIZATION> nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
