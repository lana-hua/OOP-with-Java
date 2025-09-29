public enum Make {
    TOYOTA,
    FORD,
    CHEVY,
    HONDA;

    public static boolean isValidMake(String make) {
        String upperMake = make.toUpperCase();
        switch (upperMake) {
            case "HONDA", "CHEVY", "TOYOTA", "FORD" -> {
                return true;
            }
            default -> {
                Frontend.invalidMakeMessage(make);
                return false;
            }
        }
    }
}


