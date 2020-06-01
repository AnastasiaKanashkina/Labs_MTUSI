package Lab2;
import java.util.HashMap;

/**
  * Класс  хранит  набор  открытых  и  закрытых
 * вершин,  и  предоставляет  основные  операции,  необходимые  для 
функционирования алгоритма поиска А* 
 **/
public class AStarState
{
    /** Ссылка на карту, на которой работает алгоритм A* **/
    private Map2D map;
    
    // Хештаблицы для открытых и закрытых вершин.
    private HashMap<Location, Waypoint> openWaypoints;
    private HashMap<Location, Waypoint> closedWaypoints;



    /**
     * Инициализация нового состояния объекта для применения алгоритма A*.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
        this.openWaypoints = new HashMap<Location, Waypoint>();
        this.closedWaypoints = new HashMap<Location, Waypoint>();
    }

    /**Возвращает карту, на которой работает алгоритм A*. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     *Этот метод проверяет все вершины в наборе открытых вершин и возвращает 
     *ссылку  на  вершину  с  наименьшей  общей стоимостью
     * Если  в  "открытом"  наборе  нет  вершин,  функция  возвращает NULL.         
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (openWaypoints.isEmpty())
        	return null;
        else {
        	Waypoint minCostWaypoint = null;
        	float minCost = Float.MAX_VALUE;
        	for (Waypoint wp : openWaypoints.values()) {
        		float totalCost = wp.getTotalCost();
        		if (totalCost < minCost) {
        			minCostWaypoint = wp;
        			minCost = totalCost;
        		}
        	}
        	return minCostWaypoint;
        }
    }

    /**
     *Этот метод добавляет  указанную  вершину  
     *только  в  том случае, если существующая вершина хуже новой. 
     * Если в наборе «открытых вершин» в настоящее время нет вершины для данного местоположения, 
     * то добавляется новая вершина.
     * Если  в  наборе  «открытых  вершин»  уже  есть  вершина  для  этой локации, 
     * добавляется новая вершина только в том случае, если стоимость пути до новой  вершины  
     * меньше  стоимости  пути  до  текущей.      
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Location loc = newWP.getLocation();
        if (!openWaypoints.containsKey(loc)) {
        	openWaypoints.put(loc, newWP);
        	return true;
        }
        else {
        	Waypoint oldWP = openWaypoints.get(loc);
        	if (newWP.getPreviousCost() < oldWP.getPreviousCost()) {
        		openWaypoints.put(loc, oldWP);
        		return true;
        	}
        	return false;
        }
    }


    /**Возвращает текущее количество открытых вершин**/
    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }


    /**
     *Перемещает вершину из набора «открытых вершин» в набор «закрытых  вершин»
    **/
    public void closeWaypoint(Location loc)
    {
        Waypoint wp = openWaypoints.remove(loc);
        closedWaypoints.put(loc, wp);
    }

    /**
     * Возвращает  значение  true,  если  указанное местоположение  
     * встречается  в  наборе  закрытых  вершин,  и  false  в  противном случае.
     **/
    public boolean isLocationClosed(Location loc)
    {
    	return (closedWaypoints.keySet().contains(loc));
    }
}

