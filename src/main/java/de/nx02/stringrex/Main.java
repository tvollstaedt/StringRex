package de.nx02.stringrex;

import com.mifmif.common.regex.Generex;

public class Main {

    public static void main(String[] args) {
        if(args.length > 0) {
            Generex generex = new Generex(args[0]);
            for (String line : generex.getAllMatchedStrings()) {
                System.out.println(line);
            }
        } else {
            StringRexForm.show();
        }
    }
}
