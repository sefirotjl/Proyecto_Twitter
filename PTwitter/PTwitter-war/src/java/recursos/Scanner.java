package recursos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juancho
 */
public class Scanner {

    public String verificaMail(String str) {

        String at = "@";
        String dot = ".";
        int lat = str.indexOf(at);
        int lstr = str.length();
        int ldot = str.indexOf(dot);
        // check if '@' is at the first position or at last position or absent in given email 
        if (str.indexOf(at) == -1 || str.indexOf(at) == 0
                || str.indexOf(at) == lstr) {
            return "Invalid E-mail ID";
        }
        // check if '.' is at the first position or at last position or absent in given email
        if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0
                || str.indexOf(dot) == lstr) {

            return "Invalid E-mail ID";
        }
        // check if '@' is used more than one times in given email
        if (str.indexOf(at, (lat + 1)) != -1) {
            return "Invalid E-mail ID";
        }
        // check for the position of '.'
        if (str.substring(lat - 1, lat) == dot
                || str.substring(lat + 1, lat + 2) == dot) {
            return "Invalid E-mail ID";
        }
        // check if '.' is present after two characters from location of '@'
        if (str.indexOf(dot, (lat + 2)) == -1) {
            return "Invalid E-mail ID";
        }

        // check for blank spaces in given email
        if (str.indexOf(" ") != -1) {
            return "Invalid E-mail ID";
        }
        return "OK";


    }
}
