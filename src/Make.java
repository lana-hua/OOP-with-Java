public enum Make {
    CHEVY,
    FORD,
    HONDA,
    TOYOTA;

    public static boolean isValidMake(String make) {
        String upperMake = make.toUpperCase();
        switch (upperMake) {
            case "HONDA", "CHEVY", "TOYOTA", "FORD" -> {
                return true;
            }
            default -> {
                Frontend.printInvalidMakeMessage(make);
                return false;
            }
        }
    }
}


