package teste.gamesranking.exceptions;

public class InconsistentDataException extends RuntimeException{

    public InconsistentDataException(String field1, String fielld2){
        super(String.format("%s não pode ser maior que %s", field1, fielld2));
    }
}
