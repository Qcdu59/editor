package org.ulco;

public class Utility {

	static public GraphicsObjects select(Point point, double distance, Layer layer){
		GraphicsObjects list = new GraphicsObjects();
		for (GraphicsObject graphicsobject : layer.get_m_list()) {
			if (graphicsobject.isClosed(point, distance)) {
				list.add(graphicsobject);
			}
		}
		return list;
	}
	
	static public GraphicsObjects select(Point point, double distance, Document document){
		GraphicsObjects list = new GraphicsObjects();
		for (Layer layer : document.get_m_layers()) {
			list.addAll(select(point,distance,layer));
		}
		return list;
	}
	
}
