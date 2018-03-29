package business;

public interface Validator<T> {

	public boolean validate(T t);
}
