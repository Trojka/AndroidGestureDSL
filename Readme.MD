#Android Gesture Dsl

This library provides an internal [domain specific language](http://en.wikipedia.org/wiki/Domain-specific_language) for specifying touchgestures on the Android platform.

It allows to construct sentences describing actions and conditions to be fulfilled when touching the screen of an Android device.


#Usage

##Short introduction

most of the time, when handling touch events you have a sequence of events and certain conditions on which you want to respond. If the sequence is not correct or a condition is not fulfilled, then you don't want anything to happen.

A small example will make this clear: 

Let's say you have a view like this

![Screenshot](https://github.com/sergedesmedt/AndroidGestureDSL/blob/master/androidGestureDSLSample/screen.png)

And you want to perform the following gestures:

>When you touch the screen and if you touch on any of the rectangles, then next, while moving, you want the touched rectangle to move

>When you touch the screen and you touch none of the rectangles, then next, while moving, you want to perform a panning like movement

>When you click the screen on any of the rectangles, then show an action menu

>When you long touch the screen on any of the rectangles, then show some data about the rectangle

Would it not be great if you could write sentences like:

    ontouchdown if(on rectangle) andnext move do(move selected rectangle)
    ontouchdown if(not on rectangle) andnext move do(panning)

A "click" is a composite of a touchdown and a touchup in which the touchup must be within a certain timeframe of the touchdown, so you get something like:

    ontouchdown if(on rectangle) andnext touchup if (within 1 second of touchdown) then do(show action menu)

A "long touch" requires that nothing happens for a certain timeframe:

    ontouchdown if(on rectangle) and if(nothinghappened during 4 seconds) then do(show data about rectangle)

This library is about making such sentences possible

##More detailed desription

I have two articles on [Codeproject](http://www.codeproject.com/) describing the use and workings of the DSL:

1. [A Domain Specific Language for Android touch events: Part 1: Description and usage of the DSL](http://www.codeproject.com/Articles/527608/A-Domain-Specific-Language-for-Android-touch-event)
2. [A Domain Specific Language for Android touch events: Part 2: Construction and inner workings](http://www.codeproject.com/Articles/559376/A-Domain-Specific-Language-for-Android-touch-event)


#License

The code is open sourced under the MIT License:

Copyright (c) 2014 Serge Desmedt

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.