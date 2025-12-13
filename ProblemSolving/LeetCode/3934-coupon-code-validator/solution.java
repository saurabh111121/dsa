import java.util.regex.Pattern;

class Solution {
    static class Coupon {
        String code;
        String businessLine;

        Coupon(String code, String businessLine) {
            this.code = code;
            this.businessLine = businessLine;
        }
    }


    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {

        Map<String, Integer> priority = new HashMap<>();
        priority.put("electronics", 0);
        priority.put("grocery", 1);
        priority.put("pharmacy", 2);
        priority.put("restaurant", 3);

        Pattern codePattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        List<Coupon> validCoupons = new ArrayList<>();

        for (int i = 0; i < code.length; i++) {
            if (!isActive[i]) continue;
            if (code[i] == null || code[i].isEmpty()) continue;
            if (!codePattern.matcher(code[i]).matches()) continue;
            if (!priority.containsKey(businessLine[i])) continue;

            validCoupons.add(new Coupon(code[i], businessLine[i]));
        }

        validCoupons.sort((a, b) -> {
            int blCompare = Integer.compare(
                    priority.get(a.businessLine),
                    priority.get(b.businessLine)
            );
            if (blCompare != 0) {
                return blCompare;
            }
            return a.code.compareTo(b.code);
        });

        List<String> result = new ArrayList<>();
        for (Coupon c : validCoupons) {
            result.add(c.code);
        }

        return result;
    }
}
