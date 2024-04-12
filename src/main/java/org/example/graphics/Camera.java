package org.example.graphics;

import org.example.dataTypes.Quaternion;
import org.example.enums.TypeOfMoves;
import org.example.nodes.Point;

import java.util.List;
import java.util.Vector;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.example.utils.VectorCalculator.rotateQuaternionByQuaternion;

public class Camera {
    private Point position;
    private Quaternion orientation;
    private double zoom;

    protected static final double DEFAULT_ZOOM = Math.PI / 2;
    protected static final Quaternion DEFAULT_ORIENTATION = new Quaternion(0.,0.,0.,1.);
    protected static final Point DEFAULT_POSITION = new Point(0, 0, 150);

    private static final double MOVE_STEP = 2;
    private static final double ZOOM_MIN = Math.PI / 2;
    private static final double ZOOM_STEP = 0.1;
    private static final double ZOOM_MAX = Math.PI / 64;
    private static final double ROTATION_STEP = Math.PI / 60;
    private static final double SIN = Math.sin(ROTATION_STEP / 2);
    private static final double COS = Math.cos(ROTATION_STEP / 2);

    private static final Quaternion ROTATE_LEFT = new Quaternion(COS, 0, SIN, 0);
    private static final Quaternion ROTATE_RIGHT = new Quaternion(-COS,0, SIN, 0);
    private static final Quaternion ROTATE_UP = new Quaternion(COS, SIN, 0, 0);
    private static final Quaternion ROTATE_DOWN = new Quaternion(-COS, SIN, 0, 0);
    private static final Quaternion ROTATE_CLOCKWISE = new Quaternion(COS, 0, 0, SIN);
    private static final Quaternion ROTATE_COUNTERCLOCKWISE = new Quaternion(-COS, 0, 0, SIN);


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

            case FORWARD -> position.setZ(position.getZ() - MOVE_STEP);
            case BACKWARD -> position.setZ(position.getZ() + MOVE_STEP);

            case LEFT -> position.setX(position.getX() - MOVE_STEP);
            case RIGHT -> position.setX(position.getX() + MOVE_STEP);

            case ROTATE_LEFT -> orientation = rotateQuaternionByQuaternion(ROTATE_LEFT, orientation);
            case ROTATE_RIGHT -> orientation = rotateQuaternionByQuaternion(ROTATE_RIGHT, orientation);
            case ROTATE_UP -> orientation = rotateQuaternionByQuaternion(ROTATE_UP, orientation);
            case ROTATE_DOWN -> orientation = rotateQuaternionByQuaternion(ROTATE_DOWN, orientation);
            case ROTATE_CLOCKWISE -> orientation = rotateQuaternionByQuaternion(ROTATE_CLOCKWISE, orientation);
            case ROTATE_COUNTERCLOCKWISE -> orientation = rotateQuaternionByQuaternion(ROTATE_COUNTERCLOCKWISE, orientation);

            case ZOOM_IN -> zoom = max(ZOOM_MIN, zoom - ZOOM_STEP);
            case ZOOM_OUT -> zoom = min(ZOOM_MAX, zoom + ZOOM_STEP);
        }
        System.out.println("Camera's position: " + position);
        System.out.println("Camera's orientation: " + orientation);
        return null;
    }

    public Point getPosition() {
        return position;
    }

    public Quaternion getOrientation() {
        return orientation;
    }

    public Vector<Double> getOrientationVector() {
        return new Vector<>(List.of(orientation.getX1(), orientation.getX2(), orientation.getX3()));
    }

    public double getZoomX() {
        return zoom;
    }

    public double getZoomY() {
        return zoom;
    }
}
