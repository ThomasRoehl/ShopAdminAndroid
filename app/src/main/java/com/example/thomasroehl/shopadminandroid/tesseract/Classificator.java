package com.example.thomasroehl.shopadminandroid.tesseract;

import android.provider.MediaStore;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas Roehl on 23.01.2016.
 */
public class Classificator {
    private String text;
    private List<String> resultList = new ArrayList<String>();
    private String shopName;
    private String sum;
    private List<String> shops = new ArrayList<String>();

    public void setText(String txt) {
        shops.addAll(Arrays.asList(new String[] { "REWE", "ALDI", "DM", "KAUFHOF" }));
        text = txt;
    }

    public String getShopName(){
        return this.shopName;
    }

    public String getSum(){
        return this.sum;
    }

    public void classificate() {
        listItems();
        searchForShop();
        findSum();
    }

    private void listItems() {
        text = text.replace("\n", " ");
        text = text.replace("\r", " ");
        resultList.addAll(Arrays.asList(text.split(" ")));
        for (int i = 0; i < resultList.size(); i++) {
            String s = resultList.get(i).trim();
            resultList.remove(i);
            if (s.equals(""))
                continue;
            resultList.add(s);
        }
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).isEmpty())
                resultList.remove(i);
        }
    }

    private void findSum() {
        try {
            List<Double> sums = new ArrayList<Double>();
            for (String s : resultList) {
                String r = s.replace(',', '.');
                try {
                    try {
                        Integer i = Integer.parseInt(r);
                    } catch (Exception e) {
                        Double d = Double.parseDouble(r);

                        if (d < 10000) {
                            sums.add(d);
                        }
                    }
                } catch (Exception e) {

                }

            }
//            Collections.sort(sums);
//            double max = sums.get(0);
//            for (int i = 1; i < sums.size(); i++) {
//                if (max < sums.get(i)) {
//                    max = sums.get(i);
//                }
//            }
//            sums.remove(max);
            sum = Collections.max(sums).toString();
        }
        catch(Exception e){
            sum = "-1.0";
        }
    }

    private void searchForShop() {
        List<String> sublist = resultList.subList(0, resultList.size());
        Collections.sort(sublist);
        HashMap<String, Integer> levenstein = new HashMap<String, Integer>();
        for (int i = 0; i < sublist.size(); i++) {
            for (int j = 0; j < shops.size(); j++) {
                Integer l = StringUtils.getLevenshteinDistance(sublist.get(i), shops.get(j));
                levenstein.put(sublist.get(i) + " - " + shops.get(j), l);
            }
        }

        Map.Entry<String, Integer> maxEntry = null;

        for (Map.Entry<String, Integer> entry : levenstein.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) < 0) {
                maxEntry = entry;

            }
            Log.v("OCR.java", "ENTRY: " + entry);
        }
        if(maxEntry != null)
            shopName = maxEntry.getKey().split(" - ")[1].trim();
        else
            shopName = "Nothing";

    }
}
