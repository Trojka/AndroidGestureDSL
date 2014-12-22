package be.trojkasoftware.android.gestures.languagetest;

import android.view.View;

import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;

public class SingleTouchSequenceSentenceTests extends GestureBuilder<View> {

    public SingleTouchSequenceSentenceTests()
    {
        super(null);
    }

    public TouchGesture createDownMoveUpSequence()
    {
        TouchGesture gesture = new TouchGesture("");

        this.Create(gesture).TouchDown()
                .AndNext().Move()
                .AndNext().TouchUp()
        ;

        return gesture;
    }

    public TouchGesture createDownMoveUpAndDownAgainSequence()
    {
        TouchGesture gesture = new TouchGesture("");

        this.Create(gesture).TouchDown()
                .AndNext().Move()
                .AndNext().TouchUp()
                .AndNext().TouchDown()
        ;

        return gesture;
    }

    public TouchGesture createDownMoveUpDoingSomethingSequence()
    {
        TouchGesture gesture = new TouchGesture("");

        this.Create(gesture).TouchDown().Do1(DoSomething())
                .AndNext().Move().Do1(DoSomething())
                .AndNext().TouchUp().Do1(DoSomething())
                .AndFinally(DoSomething());
        ;

        return gesture;
    }

    public TouchGesture createDownMoveUpDoingSomethingAndDownAgainSequence()
    {
        TouchGesture gesture = new TouchGesture("");

        this.Create(gesture).TouchDown().Do1(DoSomething())
                .AndNext().Move().Do1(DoSomething())
                .AndNext().TouchUp().Do1(DoSomething())
                .AndNext().TouchDown().Do1(DoSomething())
        ;

        return gesture;
    }

    IGestureAction DoSomething()
    {
        return null;
    }

}
