package lw01.Unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("washes.txt")
        );

        int count = 0;
        if (scanner.hasNextInt()) {
            count = scanner.nextInt(); 
        }

        
        WashService[] services = new WashService[count];
        int[] unitsArray = new int[count];

        for (int i = 0; i < count; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            WashService service;

            if (type.equals("MOTORCYCLE")) {
                service = new MotorcycleWash(id, days);
            } else {
                service = new CarWash(id, days);
            }

            services[i] = service;
            unitsArray[i] = units;
        }

        scanner.close();

        
        for (int i = 0; i < services.length; i++) {
            WashService service = services[i];
            int units = unitsArray[i];
            
            System.out.println(service.getId() + " | " + service.label() + " | " + service.calculateCharge(units));
        }
    }
}