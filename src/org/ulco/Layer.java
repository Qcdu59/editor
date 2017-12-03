package org.ulco;

import java.util.Vector;

public class Layer {
    public Layer() {
        group = new Group();
    }

    public Layer(String json) {
        group = new Group();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int endIndex = str.lastIndexOf("}");

        if(str.contains("groups")){
        	int groupsIndex = str.indexOf("groups");
        	parseObjects(str.substring(groupsIndex + 8, endIndex - 1));
        }
        
        parseObjects(str.substring(objectsIndex + 9, endIndex - 1));
    }

    public void add(GraphicsObject o) {
        group.add(o);
    }

    public GraphicsObject get(int index) {
        return group.get_m_objectList().elementAt(index);
    }

    public int getObjectNumber() {
        return group.size();
    }

    public int getID() {
        return group.getID();
    }

    private void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            group.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    private int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    public Vector<GraphicsObject> get_m_list(){
    	return group.get_m_objectList();
    }

    public String toJson() {
        String str = "{ type: layer, objects : { ";

        for (int i = 0; i < group.size(); ++i) {
        	if(group.get_m_objectList().elementAt(i).isSimple()) {
	            GraphicsObject element = group.get_m_objectList().elementAt(i);
	
	            str += element.toJson();
	            if (i < group.size() - 1) {
	                str += ", ";
	            }
        	}
        }
        return str + " } }";
    }

    private Group group;
}
