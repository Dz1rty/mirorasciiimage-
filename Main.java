package asciimirror;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input the file path:");
        Scanner scanner = new Scanner(System.in);
        String file = scanner.nextLine();
        ArrayList<String> lines = new ArrayList<>();

        try(Scanner scanner1 = new Scanner(new File(file))){
            while (scanner1.hasNext()){
                lines.add(scanner1.nextLine());
            }
        }catch (Exception e){
            System.out.println("File not found!");
        }
        int len = 0;
        int ind = 0;
        for (String x : lines){
            if(x.length()>len){
                len = x.length();
                ind = lines.indexOf(x);
            }
        }
        for (int i =0; i < lines.size(); i++){
            if(lines.get(i).length()<=len){
                lines.set(i,String.format("%-" + len + "s", lines.get(i)));
            }
        }

        ArrayList<String> mod = new ArrayList<>();
//< to >, [ to ], { to }, ( to ), / to \,
        for (String x : lines){
            String[] chars = x.split("");
            StringBuilder sb = new StringBuilder();
            for (String y : chars){
                if(y.equals("<")){
                    sb.append(">");
                }else if(y.equals(">")){
                    sb.append("<");
                }else if(y.equals("[")){
                    sb.append("]");
                }else if(y.equals("]")){
                    sb.append("[");
                }else if(y.equals("{")){
                    sb.append("}");
                }else if(y.equals("}")){
                    sb.append("{");
                }else if(y.equals("(")){
                    sb.append(")");
                }else if(y.equals(")")){
                    sb.append("(");
                }else if(y.equals("/")){
                    sb.append("\\");
                }else if(y.equals("\\")){
                    sb.append("/");
                }else {
                    sb.append(y);
                }
            }
            mod.add(sb.toString());
        }
        for (int i = 0; i < mod.size(); i++){
            System.out.print(lines.get(i));
            System.out.print(" | ");
            System.out.println(new StringBuilder(mod.get(i)).reverse());
        }
    }
}
