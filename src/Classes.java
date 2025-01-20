package src;

import java.util.Random;
import java.util.Objects;

interface Shape {
    double getArea();

    double getPerimeter();
}

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Радиус должен быть положительным");
        }
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Стороны должны быть положительными");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }
}

class Triangle implements Shape {
    private double a, b, c;

    public Triangle(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Стороны треугольника некорректны");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }
}

class ShapeGenerator {
    private static final Random random = new Random();

    public static Circle generateCircle() {
        return new Circle(random.nextDouble() * 10 + 1);
    }

    public static Rectangle generateRectangle() {
        return new Rectangle(random.nextDouble() * 10 + 1, random.nextDouble() * 10 + 1);
    }

    public static Triangle generateTriangle() {
        double a, b, c;
        do {
            a = random.nextDouble() * 10 + 1;
            b = random.nextDouble() * 10 + 1;
            c = random.nextDouble() * 10 + 1;
        } while (a + b <= c || a + c <= b || b + c <= a);
        return new Triangle(a, b, c);
    }
}

enum RecordType {
    NONE,
    RECORD,
    VERSION
}

class MainModel {
    private int id;
    private String name;
    private RecordType type;

    public MainModel(int id, String name, RecordType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "MainModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainModel mainModel = (MainModel) o;
        return id == mainModel.id && Objects.equals(name, mainModel.name) && type == mainModel.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}

public class Classes {
    public static void main(String[] args) {
        Circle circle = ShapeGenerator.generateCircle();
        Rectangle rectangle = ShapeGenerator.generateRectangle();
        Triangle triangle = ShapeGenerator.generateTriangle();

        System.out.println("Circle: Area = " + circle.getArea() + ", Perimeter = " + circle.getPerimeter()
                + "\nRectangle: Area = " + rectangle.getArea() + ", Perimeter = " + rectangle.getPerimeter()
                + "\nTriangle: Area = " + triangle.getArea() + ", Perimeter = " + triangle.getPerimeter());

        MainModel model1 = new MainModel(1, "Model1", RecordType.RECORD);
        MainModel model2 = new MainModel(2, "Model2", RecordType.VERSION);

        System.out.println("\n" + model1 + "\n" + model2);

        System.out.println("Are models equal? " + model1.equals(model2));
    }
}