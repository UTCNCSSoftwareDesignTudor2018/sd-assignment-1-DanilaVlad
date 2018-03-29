package business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentBLLTest {

	@Test
	void testLogin() {
		String username = "Vlad";
		String password = "pass";
		assertEquals(StudentBLL.login(username, password).get(0), "s");
		assertEquals(StudentBLL.login(username, password).get(1), username);
		assertEquals(StudentBLL.login(username, password).get(2), password);
	}

}
