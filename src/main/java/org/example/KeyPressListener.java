package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Function;

public class KeyPressListener implements KeyListener {
    private final Function<TypeOfMoves,Void> Callback;

    public KeyPressListener(Function<TypeOfMoves, Void> callback) {
        Callback = callback;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                Callback.apply(TypeOfMoves.FORWARD);
                break;
            case KeyEvent.VK_DOWN:
                Callback.apply(TypeOfMoves.BACKWARD);
                break;
            case KeyEvent.VK_LEFT:
                Callback.apply(TypeOfMoves.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                Callback.apply(TypeOfMoves.RIGHT);
                break;
            case KeyEvent.VK_U:
                Callback.apply(TypeOfMoves.UP);
                break;
            case KeyEvent.VK_J:
                Callback.apply(TypeOfMoves.DOWN);
                break;
            case KeyEvent.VK_W:
                Callback.apply(TypeOfMoves.ROTATE_UP);
                break;
            case KeyEvent.VK_S:
                Callback.apply(TypeOfMoves.ROTATE_DOWN);
                break;
            case KeyEvent.VK_A:
                Callback.apply(TypeOfMoves.ROTATE_LEFT);
                break;
            case KeyEvent.VK_D:
                Callback.apply(TypeOfMoves.ROTATE_RIGHT);
                break;
            case KeyEvent.VK_Q:
                Callback.apply(TypeOfMoves.ROTATE_COUNTERCLOCKWISE);
                break;
            case KeyEvent.VK_E:
                Callback.apply(TypeOfMoves.ROTATE_CLOCKWISE);
                break;
            case KeyEvent.VK_Z:
                Callback.apply(TypeOfMoves.ZOOM_IN);
                break;
            case KeyEvent.VK_C:
                Callback.apply(TypeOfMoves.ZOOM_OUT);
                break;
            default:
                System.out.println("Unknown key");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
