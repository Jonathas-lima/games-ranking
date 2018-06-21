package teste.gamesranking.exceptions;

public class NegativeNumberException extends RuntimeException {

    private String field;
    public NegativeNumberException(String field){

        super(String.format("%s não admite número negativo ", field));
        this.field = field;
    }

    public String getField() {
        return this.field;
    }
}
