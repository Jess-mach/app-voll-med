package med.voll.api.exception;

class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}