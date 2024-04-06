package org.example;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.function.Function;

public class KeyPressListener implements EventHandler<KeyEvent> {
    private final Function<TypeOfMoves,Void> callback;

    public KeyPressListener(Function<TypeOfMoves, Void> callback) {
        this.callback = callback;
    }
    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                callback.apply(TypeOfMoves.FORWARD);
                break;
            case DOWN:
                callback.apply(TypeOfMoves.BACKWARD);
                break;
            case LEFT:
                callback.apply(TypeOfMoves.LEFT);
                break;
            case RIGHT:
                callback.apply(TypeOfMoves.RIGHT);
                break;
            case U:
                callback.apply(TypeOfMoves.UP);
                break;
            case J:
                callback.apply(TypeOfMoves.DOWN);
                break;
            case W:
                callback.apply(TypeOfMoves.ROTATE_UP);
                break;
            case S:
                callback.apply(TypeOfMoves.ROTATE_DOWN);
                break;
            case A:
                callback.apply(TypeOfMoves.ROTATE_LEFT);
                break;
            case D:
                callback.apply(TypeOfMoves.ROTATE_RIGHT);
                break;
            case Q:
                callback.apply(TypeOfMoves.ROTATE_COUNTERCLOCKWISE);
                break;
            case E:
                callback.apply(TypeOfMoves.ROTATE_CLOCKWISE);
                break;
            case Z:
                callback.apply(TypeOfMoves.ZOOM_IN);
                break;
            case C:
                callback.apply(TypeOfMoves.ZOOM_OUT);
                break;
            default:
                System.out.println("Unknown key");
                break;
        }
        System.out.println(event.getCode());
    }
}
