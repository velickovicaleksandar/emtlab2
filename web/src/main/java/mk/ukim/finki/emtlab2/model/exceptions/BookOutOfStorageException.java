package mk.ukim.finki.emtlab2.model.exceptions;

public class BookOutOfStorageException  extends RuntimeException{
    public BookOutOfStorageException(Long id) {
        super((String.format("Book with id: %d is out of storage", id)));
    }
}
