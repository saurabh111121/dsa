class Solution {
    public String reformatDate(String date) {
        StringBuilder str = new StringBuilder();
        String day = "";
        String[] month = {"Jan","Feb","Mar","Apr","May","Jun",
                          "Jul","Aug","Sep","Oct","Nov","Dec"};
        String m = "";
        String year = "";
        boolean firstPass = false;
        boolean secondPass = false;

        for (int i = 0; i < date.length(); i++) {
            char c = date.charAt(i);

            if (!firstPass && c != ' ') {
                if (Character.isDigit(c)) {
                    day += c;
                }
            } else if (!firstPass && c == ' ') {
                firstPass = true;
            } else if (firstPass && !secondPass && c != ' ') {
                m += c;
            } else if (firstPass && !secondPass && c == ' ') {
                secondPass = true;
            } else if (secondPass && c != ' ') {
                year += c;
            }
        }

        if (day.length() == 1) day = "0" + day;

        str.append(year).append("-");

        for (int i = 0; i < month.length; i++) {
            if (m.equals(month[i])) {
                int monthNum = i + 1;
                if (monthNum < 10) str.append("0");
                str.append(monthNum);
            }
        }

        str.append("-").append(day);
        return str.toString();
    }
}




/*

Example 1:

Input: date = "20th Oct 2052"
Output: "2052-10-20"
Example 2:

Input: date = "6th Jun 1933"
Output: "1933-06-06"
Example 3:

Input: date = "26th May 1960"
Output: "1960-05-26"

    */
