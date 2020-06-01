package Lab1;
import java.util.*;


public class Lab1 {
	   
		public static double getDouble() {
			Scanner scanner=new Scanner(System.in);
			double d=scanner.nextDouble();
			return d;	        			
	    }
	    
	    private static double computeArea(Point3d p1, Point3d p2, Point3d p3) {
	    	// Расчет площади треугольника по формуле Герона
	    	double sideAB = p1.distanceTo(p2);
	    	double sideBC = p2.distanceTo(p3);
	    	double sideAC = p1.distanceTo(p3);
	    	double s = 0.5 * (sideAB + sideBC + sideAC);
	    	return Math.sqrt(s * (s - sideAB) * (s - sideBC) * (s - sideAC));
	    }
	    
	    public static void main(String[] args) {
	    	// Запрос у пользователя координат точек.
	    	System.out.println("Введите координаты (x, y, z) Точки A");
	    	double point1X = getDouble();
	    	double point1Y = getDouble();
	    	double point1Z = getDouble();
	    	
	    	System.out.println("Введите координаты (x, y, z) Точки B");
	    	double point2X = getDouble();
	    	double point2Y = getDouble();
	    	double point2Z = getDouble();
	    	
	    	System.out.println("Введите координаты (x, y, z) Точки C");
	    	double point3X = getDouble();
	    	double point3Y = getDouble();
	    	double point3Z = getDouble();
	    	
	    	// Создание  трех  объектов  типа Point3d
	    	Point3d point1 = new Point3d(point1X, point1Y, point1Z);
	    	Point3d point2 = new Point3d(point2X, point2Y, point2Z);
	    	Point3d point3 = new Point3d(point3X, point3Y, point3Z);
	    	
	    	// Если две точки равны, вывод сообщения о невозможности расчета площади
	    	if (point1.equals(point2) || point2.equals(point3) || point1.equals(point3)) {
	    		System.out.println("Две точки равны; площадь треугольника ABC не может быть расcчитана.");
	    		return;
	    	}
	    	
	    	// Расчет и вывод площади треугольника пользователю
	    	else {
	    		String output = String.format("Плодащь треугольника ABC равна %f", computeArea(point1, point2, point3));
	    		System.out.println(output);
	    	}
	    	
	    }
	}
