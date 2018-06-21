package teste.gamesranking.exceptions;

public class NameInvalidException extends RuntimeException {

    public NameInvalidException(String resourceName){
        super(String.format("%s contém caracter(es) inválidos", resourceName));
    }
}
