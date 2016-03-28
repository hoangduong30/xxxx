/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.ArrayList;

/**
 *
 * @author MyPC
 */
public class convertMoney {
    public static String convertValue(double amout){
        double thousan = amout / 1000;
        String name = "";
        Integer a = 0;
        ArrayList<String> numbers = new ArrayList<>();
        for (int i = 0; thousan >= 1; i++) {
            if (thousan < 10) {
                a = (int) thousan;
            } else {
                a = (int) (thousan-((int) thousan/10)*10);
            }
            System.out.println(i + "thousan: " + thousan);
            thousan = thousan / 10;

            switch (a) {
                case 0:
                    name = "không";
                    break;
                case 1:
                    name = "Một";
                    break;
                case 2:
                    name = "Hai";
                    break;
                case 3:
                    name = "Ba";
                    break;
                case 4:
                    name = "Bốn";
                    break;
                case 5:
                    name = "Năm";
                    break;
                case 6:
                    name = "Sáu";
                    break;
                case 7:
                    name = "Bảy";
                    break;
                case 8:
                    name = "Tám";
                    break;
                case 9:
                    name = "Chín";
                    break;
            }
            numbers.add(name);
        }
        String numberString = "";
for(int i = numbers.size()-1;i>=0;i--){
        if ((i % 3) == 0) {
              String donvi = "";
            if(!((numbers.size()>=3)&&(i==0)&&(numbers.get(i+1).equals("không"))&&(numbers.get(i+2).equals("không")))){
                
            
            if (((i+1) / 3) == 0) {
                donvi = " Ngàn ";
            } else if (((i+1) / 3) == 1) {
                donvi = " Triệu ";
            } else if (((i+1) / 3) == 2) {
                donvi = " Tỉ ";
            }
            }
                if (!numbers.get(i).equals("không")) {
                    numberString = numberString + numbers.get(i) + donvi;
                } else {
                    numberString = numberString + donvi;
                }
            
            
        } else if ((i % 3) == 1) {
            if ((!numbers.get(i).equals("không"))) {
                numberString = numberString + numbers.get(i) + " mươi "  ;
            } else if (numbers.get(i).equals("Một")) {
                numberString = numberString + " mười";
            }
            
        } else if ((i % 3) == 2) {

            if (!numbers.get(i).equals("không")) {
                numberString = numberString + numbers.get(i) + " Trăm ";
            }
        }
}

        return numberString;
    }
    
}
