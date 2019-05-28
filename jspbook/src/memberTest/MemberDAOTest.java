package memberTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import member.*;

public class MemberDAOTest {
	private MemberDAO mDao = new MemberDAO();
	
	@Test
	public void verifyIdPasswordTest() {
		int result = mDao.verifyIdPassword(100001, "rlawkqk");
		assertEquals(MemberDAO.ID_PASSWORD_MATCH, result);
	}
}
