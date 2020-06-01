package Lab1;
 	/**
* трехмерный класс точки .
**/
	public class Point3d {
	    
	    /** координата X */
	    private double xCoord;
	    
	    /** координата Y */
	    private double yCoord;
	    
	    /** координата Z */
	    private double zCoord;
	
	    /** Конструктор инициализации (x, y, z) */
	    public Point3d(double x, double y, double z) {
	        xCoord = x;
	        yCoord = y;
	        zCoord = z;
	    }
	
	    /** Конструктор по умолчанию. */
	    public Point3d() {
	        // Вызовите конструктор с тремя параметрами и определите источник.
	        this(0, 0, 0);
	    }
	    
	    /** Сравнение на равенстов двух точек. */
	    public boolean equals(Object o) {
	    	if (o.getClass() == Point3d.class) {
	    		Point3d pt = (Point3d) o;
	        	return (xCoord == pt.getX()) && (yCoord == pt.getY()) && (zCoord == pt.getZ());
	    	}
	    	else 
	    		return false;
	    }
	    
	    /** Расчет расстояния между двумя точками. */
	    public double distanceTo(Point3d pt) {
	    	double deltaX = xCoord - pt.getX();
	    	double deltaY = yCoord - pt.getY();
	    	double deltaZ = zCoord - pt.getZ();
	    	return Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
	    }
	
	    /** Возвращение координаты X. */
	    public double getX() {
	        return xCoord;
	    }
	
	    /** Возвращение координаты Y. */
	    public double getY() {
	        return yCoord;
	    }
	    
	    /** Возвращение координаты Z. */
	    public double getZ() {
	        return zCoord;
	    }
	
	    /** Установка значения координаты X. */
	    public void setX(double val) {
	        xCoord = val;
	    }
	
	    /**Установка значения координаты Y. */
	    public void setY(double val) {
	        yCoord = val;
	    }
	    
	    /** Установка значения координаты Z. */
	    public void setZ(double val) {
	        zCoord = val;
	    }
	}