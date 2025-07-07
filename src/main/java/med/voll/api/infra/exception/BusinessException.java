package med.voll.api.infra.exception;

class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}