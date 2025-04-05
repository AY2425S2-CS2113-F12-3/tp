package mindexpander.data.question;

/**
 * Enum representing types of questions.
 */
public enum QuestionType {
    FITB("fitb"),
    MCQ("mcq"),
    TF("tf");

    private final String type;

    QuestionType(String type) {
        this.type = type;
    }

    /**
     * Returns the string representation of the type.
     *
     * @return The type string.
     */
    public String getType() {
        return type;
    }

    /**
     * Checks if a string matches any valid question type.
     *
     * @param input The input string.
     * @return true if valid, false otherwise.
     */
    public static boolean isValidType(String input) {
        for (QuestionType qt : values()) {
            if (qt.getType().equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a string to the corresponding QuestionType.
     *
     * @param input The input string.
     * @return The matching QuestionType.
     * @throws IllegalArgumentException if input is invalid.
     */
    public static QuestionType fromString(String input) {
        for (QuestionType qt : values()) {
            if (qt.getType().equalsIgnoreCase(input)) {
                return qt;
            }
        }
        throw new IllegalArgumentException("Invalid question type: " + input);
    }

    public static String allTypes() {
        StringBuilder sb = new StringBuilder();
        for (QuestionType qt : values()) {
            sb.append(qt.getType()).append("/");
        }
        sb.setLength(Math.max(sb.length() - 1, 0));
        return sb.toString();
    }

}
