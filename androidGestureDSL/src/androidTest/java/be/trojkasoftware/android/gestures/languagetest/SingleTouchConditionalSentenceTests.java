package be.trojkasoftware.android.gestures.languagetest;


import android.view.View;

import be.trojkasoftware.android.gestures.IGestureAction;
import be.trojkasoftware.android.gestures.IGestureCondition;
import be.trojkasoftware.android.gestures.TouchGesture;
import be.trojkasoftware.android.gestures.dsl.GestureBuilder;

public class SingleTouchConditionalSentenceTests extends GestureBuilder<View> {

    public SingleTouchConditionalSentenceTests()
    {
        super(null);
    }

    public TouchGesture createDownConditionalIfSequence()
    {
        TouchGesture gesture = new TouchGesture("");

        this.Create(gesture).TouchDown().If(Condition()).Do2(DoSomething())
                .AndNext().Move()
                .AndNext().TouchUp()
        ;

        this.Create(gesture).TouchDown().If(Condition()).AndIf(Condition()).Do2(DoSomething())
                .AndNext().Move()
                .AndNext().TouchUp()
        ;

        this.Create(gesture).TouchDown()
                    .If(Condition()).Do2(DoSomething())
                    .Else().Do3(DoSomething())
                .AndNext().Move()
                .AndNext().TouchUp()
        ;

        this.Create(gesture).TouchDown()
                    .If(Condition()).Do2(DoSomething())
                    .Else().If(Condition()).Do2(DoSomething())
                .AndNext().Move()
                .AndNext().TouchUp()
        ;

        return gesture;
    }

    public TouchGesture createMoveConditionalIfSequence()
    {
        TouchGesture gesture = new TouchGesture("");

        this.Create(gesture).TouchDown()
                .AndNext().Move().If(Condition()).Do2(DoSomething())
                .AndNext().TouchUp()
        ;

        this.Create(gesture).TouchDown()
                .AndNext().Move().If(Condition()).AndIf(Condition()).Do2(DoSomething())
                .AndNext().TouchUp()
        ;

        this.Create(gesture).TouchDown()
                .AndNext().Move()
                .If(Condition()).Do2(DoSomething())
                .Else().Do3(DoSomething())
                .AndNext().TouchUp()
        ;

        this.Create(gesture).TouchDown()
                .AndNext().Move()
                .If(Condition()).Do2(DoSomething())
                .Else().If(Condition()).Do2(DoSomething())
                .AndNext().TouchUp()
        ;

        return gesture;
    }

    public TouchGesture createUpConditionalIfSequence()
    {
        TouchGesture gesture = new TouchGesture("");

        this.Create(gesture).TouchDown()
                .AndNext().Move()
                .AndNext().TouchUp().If(Condition()).Do2(DoSomething())
        ;

        this.Create(gesture).TouchDown()
                .AndNext().Move()
                .AndNext().TouchUp().If(Condition()).AndIf(Condition()).Do2(DoSomething())
        ;

        this.Create(gesture).TouchDown()
                .AndNext().Move()
                .AndNext().TouchUp()
                .If(Condition()).Do2(DoSomething())
                .Else().Do3(DoSomething())
        ;

        this.Create(gesture).TouchDown()
                .AndNext().Move()
                .AndNext().TouchUp()
                .If(Condition()).Do2(DoSomething())
                .Else().If(Condition()).Do2(DoSomething())
        ;

        return gesture;
    }

    IGestureCondition Condition()
    {
        return null;
    }

    IGestureAction DoSomething()
    {
        return null;
    }

}
