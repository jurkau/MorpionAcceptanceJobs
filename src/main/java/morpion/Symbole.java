package morpion;

public enum Symbole {

    X('X'),
    O('O'),
    BLANK(' ');

    private final char symbole;

    Symbole(char initSymbole) {
        this.symbole = initSymbole;
    }

    public boolean isMarked() {return this != BLANK; }

    public char getSymbole() {return this.symbole; }

    @Override
    public String toString() { return String.valueOf(symbole); }
}
