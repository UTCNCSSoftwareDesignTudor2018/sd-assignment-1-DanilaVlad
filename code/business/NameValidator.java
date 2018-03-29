package business;

import java.util.regex.Pattern;

import data.StudentEntity;

public class NameValidator implements Validator<StudentEntity> {
	private static final String NAME_PATTERN = "^[\\p{Alnum}]+$"; 

	@Override
	public boolean validate(StudentEntity student) {
		Pattern pattern = Pattern.compile(NAME_PATTERN);
		if (!pattern.matcher(student.getName()).matches()) {
			return false;
		}
		return true;
	}

}
