package com.haversine;

import com.haversine.domain.Suburb;
import com.haversine.domain.SuburbKey;
import com.haversine.service.SuburbService;
import com.haversine.service.impl.SuburbServiceImpl;
import com.haversine.utility.ProximityEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {

        SuburbService suburbService = new SuburbServiceImpl();

        List<Suburb> suburbList =  suburbService.getDataFromFile("aus_suburbs.json");
        Map<SuburbKey, Suburb> suburbMap =  suburbService.generateDataMap(suburbList);
        List<Suburb> suburbListValid =  new ArrayList<>(suburbMap.values());

        Scanner command = new Scanner(System.in);
        boolean running = true;
        do {

            System.out.println(" ");
            System.out.print("> Please enter a suburb name: ");
            String suburbName = command.nextLine();
            String postcode;

            do {
                System.out.print("> Please enter the postcode: ");
                postcode = command.nextLine();
            } while(!postcode.matches("[0-9]+"));
            System.out.println(" ");

            Suburb chosenSuburb = suburbMap.get(new SuburbKey(Integer.parseInt(postcode), suburbName.toUpperCase()));

            if (chosenSuburb != null) {
                List<Suburb> closeSuburbs = suburbService.getCloseSuburbs(chosenSuburb, suburbListValid);

                if (closeSuburbs != null) {
                    System.out.println(ProximityEnum.NEARBY.getValue());
                    closeSuburbs.stream()
                            .filter(s->s.getProximityType().equals(ProximityEnum.FRINGE.toString()))
                            .map(s->"    "+s.getLocality()+" "+s.getPcode())
                            .forEach(System.out::println);

                    System.out.println(" ");
                    System.out.println(ProximityEnum.FRINGE.getValue());
                    closeSuburbs.stream()
                            .filter(s->s.getProximityType().equals(ProximityEnum.FRINGE.toString()))
                            .map(s->"    "+s.getLocality()+" "+s.getPcode())
                            .forEach(System.out::println);
                }

                System.out.println(" ");
                System.out.print("Search more? [ Y / N ] >>  ");
                if(!command.nextLine().equalsIgnoreCase("y"))
                    running = false;

            } else {
                System.out.println("Suburb was not found. Please input another query. ");
            }

        } while(running);

        command.close();
    }
}
