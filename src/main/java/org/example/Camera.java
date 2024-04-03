package org.example;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Camera {
    private Point position;
    private Quaternion orientation;
    private double zoom;

    private static final double DEFAULT_ZOOM = Math.PI / 3;
    private static final Quaternion DEFAULT_ORIENTATION = new Quaternion(1, 0, 0, 0);
    private static final Point DEFAULT_POSITION = new Point(0, 0, 0);

    private static final double MOVE_STEP = 0.5;
    private static final double ZOOM_MIN = Math.PI / 2;
    private static final double ZOOM_STEP = 0.1;
    private static final double ZOOM_MAX = Math.PI / 64;
    private static final double ROTATION_STEP = Math.PI / 60;


    public Camera(Point position, Quaternion orientation, double zoom) {
        this.position = position;
        this.orientation = orientation;
        this.zoom = zoom;
    }
    public Camera() {
        this(DEFAULT_POSITION, DEFAULT_ORIENTATION, DEFAULT_ZOOM);
    }

    public Void move(TypeOfMoves move){
        switch (move) {
            case UP -> position.setY(position.getY() + MOVE_STEP);
            case DOWN -> position.setY(position.getY() - MOVE_STEP);
            case FORWARD -> position.setZ(position.getZ() + MOVE_STEP);
            case BACKWARD -> position.setZ(position.getZ() - MOVE_STEP);
            case LEFT -> position.setX(position.getX() - MOVE_STEP);
            case RIGHT -> position.setX(position.getX() + MOVE_STEP);
            case ROTATE_LEFT -> orientation = orientation.multiply(new Quaternion(Math.cos(ROTATION_STEP / 2), 0, Math.sin(ROTATION_STEP / 2), 0));
            case ROTATE_RIGHT -> orientation = orientation.multiply(new Quaternion(Math.cos(ROTATION_STEP / 2), 0, -Math.sin(ROTATION_STEP / 2), 0));
            case ROTATE_UP -> orientation = orientation.multiply(new Quaternion(Math.cos(ROTATION_STEP / 2), Math.sin(ROTATION_STEP / 2), 0, 0));
            case ROTATE_DOWN -> orientation = orientation.multiply(new Quaternion(Math.cos(ROTATION_STEP / 2), -Math.sin(ROTATION_STEP / 2), 0, 0));
            case ROTATE_CLOCKWISE -> orientation = orientation.multiply(new Quaternion(Math.cos(ROTATION_STEP / 2), 0, 0, Math.sin(ROTATION_STEP / 2)));
            case ROTATE_COUNTERCLOCKWISE -> orientation = orientation.multiply(new Quaternion(Math.cos(ROTATION_STEP / 2), 0, 0, -Math.sin(ROTATION_STEP / 2)));
            case ZOOM_IN -> zoom = max(ZOOM_MIN, zoom - ZOOM_STEP);
            case ZOOM_OUT -> zoom = min(ZOOM_MAX, zoom + ZOOM_STEP);
        }
        //print();
        return null;
    }

    public Point getPosition() {
        return position;
    }

    public Quaternion getOrientation() {
        return orientation;
    }

    public double getZoom() {
        return zoom;
    }

    public void print(){
        System.out.println("Position: " + position + "\nOrientation: " + orientation + "\nZoom: " + zoom);
    }
}
