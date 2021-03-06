package biz.a7software.slimmyinvoice.helper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * The AreaList class manages a list of Area objects.
 */
public class AreaList implements Iterable<Area> {

    private List<Area> list = null;

    public AreaList(String areas) {
        init(areas);
    }

    // Builds an Area list from formatted string.
    private void init(String areas) {
        if (areas == null || areas.isEmpty()) {
            return;
        }
        StringReader sr = new StringReader(areas);
        Properties ocrAreas = new Properties();
        try {
            ocrAreas.load(sr);
            int size = ocrAreas.size();
            Area[] tempList = new Area[size];
            for (Object key : ocrAreas.keySet()) {
                String name = key.toString();
                Area area = Area.create(ocrAreas.getProperty(name));
                if (area != null) {
                    tempList[new Integer(name)] = area;
                }
            }
            list = Arrays.asList(tempList);
        } catch (IOException e) {
            e.printStackTrace();
            // Should really never happen
        }
    }

    // Gets last area of the list.
    public Area getLast() {
        if (list.size() >= 1) {
            return list.get(list.size() - 1);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<Area> iterator() {
        return list.iterator();
    }

    public int size() {
        return list.size();
    }
}