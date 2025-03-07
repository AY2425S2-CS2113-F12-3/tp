package mindexpander.data.question;

public enum QuestionType {
    FITB("fitb");

    private final String type;

    QuestionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static boolean isValidType(String input) {
        for (QuestionType qt : values()) {
            if (qt.getType().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
